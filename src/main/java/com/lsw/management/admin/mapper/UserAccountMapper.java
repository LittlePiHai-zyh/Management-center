package com.lsw.management.admin.mapper;

import com.lsw.management.admin.model.dto.user.UserQueryDto;
import com.lsw.management.admin.model.po.user.UserAccount;
import com.lsw.management.admin.model.vo.user.UserVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author zhangyh
 * @Date 2023/4/6 15:55
 * @desc
 */
public interface UserAccountMapper extends Mapper<UserAccount> {

    List<UserVo> selectInfoPageList(UserQueryDto queryDto);
}
