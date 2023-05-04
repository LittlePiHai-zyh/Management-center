package com.lsw.management.admin.service.impl;

import com.lsw.management.admin.mapper.ProjectMapper;
import com.lsw.management.admin.mapper.ScoresMapper;
import com.lsw.management.admin.model.dto.score.ScoreAddDto;
import com.lsw.management.admin.model.dto.score.ScoreQueryDto;
import com.lsw.management.admin.model.po.project.Project;
import com.lsw.management.admin.model.po.scores.ScoreVo;
import com.lsw.management.admin.model.po.scores.Scores;
import com.lsw.management.admin.service.ScoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/5/4  11:55
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Resource
    ScoresMapper scoresMapper;

    @Resource
    ProjectMapper projectMapper;

    @Resource
    TransactionTemplate transactionTemplate;


    @Override
    public Integer add(ScoreAddDto score, HttpServletRequest request) {
     return   transactionTemplate.execute(status -> {
            Scores scores = new Scores();
            BeanUtils.copyProperties(score, scores);
            scores.setCreateTime(new Date());
            scores.setDeleted((byte) 0);
            Project project = new Project();
            project.setStatus(4);
            project.setUpdateTime(new Date());
            project.setId(score.getProjectId());
            projectMapper.updateByPrimaryKeySelective(project);
            return scoresMapper.insert(scores);
        });
    }

    @Override
    public List<ScoreVo> listAll(ScoreQueryDto queryDto) {
        return scoresMapper.listAll(queryDto);
    }
}
