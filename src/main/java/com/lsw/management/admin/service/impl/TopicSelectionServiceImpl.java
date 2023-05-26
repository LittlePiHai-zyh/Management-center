package com.lsw.management.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lsw.management.admin.mapper.DesignProjectAuditFlowMapper;
import com.lsw.management.admin.mapper.TopicSelectionMapper;
import com.lsw.management.admin.model.dto.topic.selection.TopicSelectionAddDto;
import com.lsw.management.admin.model.dto.topic.selection.TopicSelectionQueryDto;
import com.lsw.management.admin.model.dto.topic.selection.TopicSelectionUpdateDto;
import com.lsw.management.admin.model.po.designProjectAuditFlow.DesignProjectAuditFlow;
import com.lsw.management.admin.model.po.topic.selection.TopicSelection;
import com.lsw.management.admin.model.po.user.UserAccount;
import com.lsw.management.admin.model.vo.PageInfoVo;
import com.lsw.management.admin.model.vo.topic.selection.MajorVo;
import com.lsw.management.admin.model.vo.topic.selection.StudentTypeVo;
import com.lsw.management.admin.model.vo.topic.selection.TopicSelectionVo;
import com.lsw.management.admin.service.TopicSelectionService;
import com.lsw.management.admin.service.UserAccountService;
import com.lsw.management.common.constants.ErrorCode;
import com.lsw.management.common.constants.MajorEnum;
import com.lsw.management.common.constants.TypeEnum;
import com.lsw.management.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/29  22:13
 */
@Service
public class TopicSelectionServiceImpl implements TopicSelectionService {

    @Resource
    TopicSelectionMapper topicSelectionMapper;

    @Resource
    UserAccountService userAccountService;

    @Resource
    TransactionTemplate transactionTemplate;

    @Resource
    DesignProjectAuditFlowMapper designProjectAuditFlowMapper;

    @Override
    public Integer add(TopicSelectionAddDto addDto, HttpServletRequest request) {
        return transactionTemplate.execute(status -> {
            TopicSelection topicSelection = new TopicSelection();
            BeanUtils.copyProperties(addDto, topicSelection);
            topicSelection.setCreateTime(new Date());
            topicSelection.setDeleted((byte) 0);
            topicSelection.setStudent(0);
            UserAccount currentUser = userAccountService.getCurrentUser(request);
            topicSelection.setTeacherId(currentUser.getId());
            topicSelectionMapper.insert(topicSelection);
            DesignProjectAuditFlow designProjectAuditFlow = new DesignProjectAuditFlow();
            designProjectAuditFlow.setState(0);
            designProjectAuditFlow.setDeleted((byte)0);
            designProjectAuditFlow.setTopicId(topicSelection.getId());
            return designProjectAuditFlowMapper.insert(designProjectAuditFlow);
        });
    }

    @Override
    public Integer delete(String ids) {
        if (StringUtils.isBlank(ids)) {
            throw new BusinessException(ErrorCode.INVALID_PARAMS);
        }
        String[] idArr = ids.split(",");
        AtomicInteger atomicInteger = new AtomicInteger();
        Arrays.stream(idArr).map(Integer::parseInt).forEach(id -> {
          transactionTemplate.execute(status -> {
              //题目未审核或审核失败才能删除
              DesignProjectAuditFlow  DesignProjectAuditFlowInfo= designProjectAuditFlowMapper.selectCanDeleteInfo(id);
              if(DesignProjectAuditFlowInfo!=null){
                  TopicSelection topicSelection = TopicSelection.builder()
                          .id(id)
                          .deleted((byte) 1)
                          .updateTime(new Date())
                          .build();
                  int i = topicSelectionMapper.updateByPrimaryKeySelective(topicSelection);
                  atomicInteger.getAndAdd(i);
                  DesignProjectAuditFlow designProjectAuditFlow = new DesignProjectAuditFlow();
                  designProjectAuditFlow.setDeleted((byte)1);
                  Example example = new Example(DesignProjectAuditFlow.class);
                  example.createCriteria()
                          .andEqualTo(DesignProjectAuditFlow.DELETED,0)
                          .andEqualTo(DesignProjectAuditFlow.TOPIC_ID,id);
                  return designProjectAuditFlowMapper.updateByExampleSelective(designProjectAuditFlow,example);
              }
             return null;
          });
        });
        return atomicInteger.get();
    }

    @Override
    public Integer update(TopicSelectionUpdateDto updateDto) {
      return  transactionTemplate.execute(status -> {
          //更新题目
            TopicSelection topicSelection = new TopicSelection();
            BeanUtils.copyProperties(updateDto, topicSelection);
            topicSelection.setUpdateTime(new Date());
            topicSelectionMapper.updateByPrimaryKeySelective(topicSelection);
            DesignProjectAuditFlow designProjectAuditFlow = new DesignProjectAuditFlow();
            //修改审核状态，再次审核
            designProjectAuditFlow.setState(0);
          designProjectAuditFlow.setDeleted((byte)0);
            designProjectAuditFlow.setTopicId(updateDto.getId());
            Example example = new Example(DesignProjectAuditFlow.class);
            example.createCriteria()
                    .andEqualTo(DesignProjectAuditFlow.DELETED,0)
                    .andEqualTo(DesignProjectAuditFlow.TOPIC_ID,updateDto.getId());
            designProjectAuditFlowMapper.deleteByExample(example);
         return   designProjectAuditFlowMapper.insert(designProjectAuditFlow);
        });
    }

    @Override
    public List<TopicSelectionVo> listAll(TopicSelectionQueryDto queryDto) {
        List<TopicSelectionVo> topicSelectionVos = topicSelectionMapper.pageList(queryDto);
        topicSelectionVos.forEach(topicSelectionVo -> {
            if((topicSelectionVo.getDepartmentAuditResult()!=null&&topicSelectionVo.getDepartmentAuditResult()==0)||
                    (topicSelectionVo.getSchoolAuditResult()!=null&&topicSelectionVo.getSchoolAuditResult()==0)){
                topicSelectionVo.setResult("驳回");
            }else if((topicSelectionVo.getDepartmentAuditResult()!=null&&topicSelectionVo.getDepartmentAuditResult()==1)||
                    (topicSelectionVo.getSchoolAuditResult()!=null&&topicSelectionVo.getSchoolAuditResult()==1)){
                topicSelectionVo.setResult("通过");
            }else{
                topicSelectionVo.setResult("待定");
            }
        });
        return topicSelectionVos;
    }

    @Override
    public PageInfoVo<TopicSelectionVo> pageList(TopicSelectionQueryDto queryDto) {
        int page = 1;
        int limit = 10;
        if (queryDto.getPage() != null) {
            page = queryDto.getPage();
        }
        if (queryDto.getLimit() != null) {
            limit = queryDto.getLimit();
        }
        PageHelper.startPage(page, limit, "create_time desc");
        Page<TopicSelectionVo> pageList = (Page<TopicSelectionVo>) topicSelectionMapper.pageList(queryDto);
        PageInfoVo<TopicSelectionVo> pageInfoVo = new PageInfoVo<>();
        pageInfoVo.setPages(pageList.getPages());
        pageInfoVo.setTotal(pageList.getTotal());
        pageInfoVo.setData(pageList.getResult());
        return pageInfoVo;
    }

    @Override
    public List<MajorVo> getMajor() {
        List<MajorVo> res = new ArrayList<>();
        for (MajorEnum value : MajorEnum.values()) {
            MajorVo majorVo = new MajorVo();
            majorVo.setName(value.getName());
            majorVo.setCode(value.getCode());
            res.add(majorVo);
        }
        return res;
    }

    @Override
    public List<StudentTypeVo> getStudentType() {
        List<StudentTypeVo> res = new ArrayList<>();
        Arrays.stream(TypeEnum.values())
                .forEach(studentTypeEnum -> {
                    StudentTypeVo studentTypeVo = new StudentTypeVo();
                    studentTypeVo.setCode(studentTypeEnum.getCode());
                    studentTypeVo.setName(studentTypeEnum.getName());
                    res.add(studentTypeVo);
                });
        return res;
    }

    @Override
    public List<TopicSelectionVo> beApproved(TopicSelectionQueryDto queryDto) {
        return topicSelectionMapper.beApproved(queryDto);
    }
}
