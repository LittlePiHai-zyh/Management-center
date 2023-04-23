package com.zhangyh.management.admin.mapper;

import com.zhangyh.management.admin.model.dto.UserQueryDto;
import com.zhangyh.management.admin.model.po.UserAccount;
import com.zhangyh.management.admin.model.vo.UserVo;
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
