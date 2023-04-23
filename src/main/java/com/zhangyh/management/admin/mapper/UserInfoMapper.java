package com.zhangyh.management.admin.mapper;

import com.zhangyh.management.admin.model.po.UserInfo;
import com.zhangyh.management.admin.model.vo.UserVo;
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
