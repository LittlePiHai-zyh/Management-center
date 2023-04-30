package com.lsw.management.admin.mapper;

import com.lsw.management.admin.model.po.user.UserInfo;
import com.lsw.management.admin.model.vo.user.UserVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhangyh
 * @Date 2023/4/7 12:17
 * @desc
 */
public interface UserInfoMapper extends Mapper<UserInfo> {
    UserVo selectUserInfo(@Param("id") Integer id);
}
