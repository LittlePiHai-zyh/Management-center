package com.zhangyh.management.admin.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhangyh.management.common.util.table.sync.annotation.Field;
import com.zhangyh.management.common.util.table.sync.annotation.IgnoreField;
import com.zhangyh.management.common.util.table.sync.annotation.TableSync;
import com.zhangyh.management.common.util.table.sync.sql.table.mysql.index.PRI;
import com.zhangyh.management.common.util.table.sync.sql.table.mysql.index.UNI;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangyh
 * @Date 2023/4/6 15:29
 * @desc
 */
@TableSync(tableName = "user_account",tableComment = "账户表")
@Data
@Table(name = "user_account")
public class UserAccount implements Serializable {

    public static final String ID="id";
    public static final String USERNAME="username";
    public static final String PASSWORD="password";
    public static final String PERMISSIONS="roles";
    public static final String DELETED="deleted";
    public static final String STATE="state";
    public static final String EXPIRED_TIME="expiredTime";
    public static final String CREATE_TIME="createTime";
    public static final String UPDATE_TIME="updateTime";

    @IgnoreField
    private static final long serialVersionUID = 1L;

    @Field(field = "id",comment = "主键id",index = PRI.class,autoIncrement = true,allowNull = false)
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "username")
    @Field(field = "username",comment = "账号",allowNull = false,index = UNI.class)
    private String username;

    @Field(field = "password",comment = "密码",allowNull = false)
    @Column(name = "password")
    private String password;

    @Field(field = "roles",comment = "用户权限 格式：1,2 数字对应枚举PermissionEnum的code")
    @Column(name = "roles")
    private String roles;

    @Field(field = "deleted",defaultValue = "0",comment = "是否删除 0否 1是",length = 2)
    @Column(name = "deleted")
    private Byte deleted;

    @Field(field = "state",defaultValue = "0",comment = "账号状态")
    @Column(name = "state")
    private Integer state;

    @Field(field = "expired_time",comment = "账号过期时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "expired_time")
    private Date expiredTime;

    @Field(field = "create_time",comment = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    @Field(field = "update_time",comment = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time")
    private Date updateTime;

}
