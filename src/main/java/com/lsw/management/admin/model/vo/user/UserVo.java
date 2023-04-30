package com.lsw.management.admin.model.vo.user;

import lombok.Data;

import java.util.Date;

/**
 * @author zhangyh
 * @Date 2023/4/7 14:09
 * @desc
 */
@Data
public class UserVo {

    private Integer id;

    private String username;

    private String name;

    private String roles;

    private Integer state;

    private Integer gender;

    private Date birthday;

    private String mobile;

    private String email;
}
