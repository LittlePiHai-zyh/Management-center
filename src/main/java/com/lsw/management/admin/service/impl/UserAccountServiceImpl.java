package com.lsw.management.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lsw.management.admin.annotation.PermissionEnum;
import com.lsw.management.admin.mapper.UserAccountMapper;
import com.lsw.management.admin.mapper.UserInfoMapper;
import com.lsw.management.admin.model.dto.user.PermissionVo;
import com.lsw.management.admin.model.dto.user.UserLoginDto;
import com.lsw.management.admin.model.dto.user.UserQueryDto;
import com.lsw.management.admin.model.dto.user.UserRegistryDto;
import com.lsw.management.admin.model.po.user.UserAccount;
import com.lsw.management.admin.model.po.user.UserInfo;
import com.lsw.management.admin.model.vo.user.UserAccountVo;
import com.lsw.management.admin.model.vo.user.UserVo;
import com.lsw.management.admin.service.UserAccountService;
import com.lsw.management.common.constants.ErrorCode;
import com.lsw.management.common.constants.GlobalConstants;
import com.lsw.management.admin.model.vo.PageInfoVo;
import com.lsw.management.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangyh
 * @Date 2023/4/6 16:34
 * @desc
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final Logger log = LoggerFactory.getLogger(UserAccountServiceImpl.class);

    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "zhangyh";

    @Resource
    UserAccountMapper userAccountMapper;

    @Resource
    UserInfoMapper userInfoMapper;

    @Resource
    TransactionTemplate transactionTemplate;

    @Override
    public UserAccount getCurrentUser(HttpServletRequest request) {
        Object user = request.getSession().getAttribute(GlobalConstants.SESSION_KEY);
        UserAccountVo currentUser = (UserAccountVo) user;
        UserAccount userAccount;
        if (currentUser == null) {
            userAccount = new UserAccount();
            userAccount.setUsername("anonymous");
        } else {
            Example example = new Example(UserAccount.class);
            example.createCriteria().andEqualTo(UserAccount.ID, currentUser.getId());
            userAccount = userAccountMapper.selectOneByExample(example);
            if (userAccount == null) {
                throw new BusinessException(ErrorCode.NOT_EXIST_USER);
            }
        }
        return userAccount;
    }

    @Override
    public UserAccountVo baseLogin(UserLoginDto user, HttpServletRequest request) {
        String password = user.getPassword();
        Example example = new Example(UserAccount.class);
        example.createCriteria()
                .andEqualTo(UserAccount.USERNAME, user.getUsername());
        UserAccount userAccount = userAccountMapper.selectOneByExample(example);
        if (userAccount == null) {
            throw new BusinessException(ErrorCode.NOT_EXIST_USER);
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        if (!encryptPassword.equals(userAccount.getPassword())) {
            throw new BusinessException(ErrorCode.PASSWORD_ERROR);
        }
        UserAccountVo userAccountVo = new UserAccountVo(userAccount);
        //用户态保存
        request.getSession().setAttribute(GlobalConstants.SESSION_KEY, userAccountVo);
        return userAccountVo;
    }

    @Override
    public Integer userRegister(UserRegistryDto registryDto) {
        if (!registryDto.getPassword().equals(registryDto.getCheckPassword())) {
            throw new BusinessException(ErrorCode.INVALID_PARAMS, "两次密码一样");
        }
        return transactionTemplate.execute(status -> {
            String userAccount = registryDto.getUsername();
            Example example = new Example(UserAccount.class);
            example.createCriteria()
                    .andEqualTo(UserAccount.USERNAME, userAccount);
            if (userAccountMapper.selectCountByExample(example) > 0) {
                throw new BusinessException(ErrorCode.ACCOUNT_DUPLICATE);
            }
            //组装账号信息
            UserAccount uAccount = new UserAccount();
            uAccount.setCreateTime(new Date());
            uAccount.setUsername(userAccount);
            uAccount.setRoles("1");
            // 2. 加密
            String encryptPassword = DigestUtils.md5DigestAsHex((SALT + registryDto.getPassword()).getBytes());
            uAccount.setPassword(encryptPassword);
            userAccountMapper.insert(uAccount);
            //组装用户信息
            UserInfo userInfo = new UserInfo();
            userInfo.setAccountId(uAccount.getId());
            userInfo.setCreateTime(new Date());
            BeanUtils.copyProperties(registryDto, userInfo);
            return userInfoMapper.insert(userInfo);
        });
    }

    @Override
    public PageInfoVo<UserVo> selectInfoPageList(UserQueryDto queryDto) {
        int page = 1;
        int limit = 10;
        if (queryDto.getPage() != null) {
            page = queryDto.getPage();
        }
        if (queryDto.getLimit() != null) {
            limit = queryDto.getLimit();
        }
        PageHelper.startPage(page, limit, "a.create_time desc");
        Page<UserVo> pageList = (Page<UserVo>) userAccountMapper.selectInfoPageList(queryDto);
        PageInfoVo<UserVo> pageInfoVo = new PageInfoVo<>();
        pageInfoVo.setPages(pageList.getPages());
        pageInfoVo.setTotal(pageList.getTotal());
        pageInfoVo.setData(pageList.getResult());
        return pageInfoVo;
    }

    @Override
    public Integer removeById(String ids) {
        if (StringUtils.isBlank(ids)) {
            throw new BusinessException(ErrorCode.INVALID_PARAMS);
        }
        return transactionTemplate.execute(status -> {
            String[] id = ids.split(",");
            AtomicInteger atomicInteger = new AtomicInteger();
            Arrays.stream(id).forEach(account -> {
                UserAccount userAccount = new UserAccount();
                userAccount.setId(Integer.parseInt(account));
                userAccount.setDeleted((byte) 0);
                userAccountMapper.updateByPrimaryKeySelective(userAccount);
                Example example = new Example(UserInfo.class);
                example.createCriteria().andEqualTo(UserInfo.ACCOUNT_ID, account);
                UserInfo userInfo = new UserInfo();
                userInfo.setDeleted((byte) 0);
                userInfoMapper.updateByExampleSelective(userInfo, example);
                atomicInteger.getAndIncrement();
            });
            return atomicInteger.get();
        });
    }

    @Override
    public UserVo getById(Integer id) {
        return userInfoMapper.selectUserInfo(id);
    }

    @Override
    public List<PermissionVo> getPermissions() {
        List<PermissionVo> res = new ArrayList<>();
        PermissionEnum[] values = PermissionEnum.values();
        for (PermissionEnum permissionEnum : values) {
            PermissionVo permissionVo = new PermissionVo();
            permissionVo.setName(permissionEnum.getName());
            permissionVo.setValue(permissionEnum.getCode());
            res.add(permissionVo);
        }
        return res;
    }


}
