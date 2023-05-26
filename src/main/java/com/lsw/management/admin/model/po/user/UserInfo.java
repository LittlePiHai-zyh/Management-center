package com.lsw.management.admin.model.po.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lsw.management.common.util.table.sync.annotation.Field;
import com.lsw.management.common.util.table.sync.annotation.IgnoreField;
import com.lsw.management.common.util.table.sync.annotation.TableSync;
import com.lsw.management.common.util.table.sync.sql.table.mysql.index.PRI;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lsw
 * @Date 2023/4/6 16:15
 * @desc
 */
@TableSync(tableName = "user_info",tableComment = "用户信息")
@Table(name = "user_info")
@Data
public class UserInfo implements Serializable {

    public static final String ID="id";
    public static final String NAME="name";
    public static final String ACCOUNT_ID="accountId";
    public static final String GENDER="gender";
    public static final String MOBILE="mobile";
    public static final String EMAIL="email";
    public static final String DELETED="deleted";
    public static final String BIRTHDAY="birthday";
    public static final String CREATE_TIME="createTime";
    public static final String UPDATE_TIME="updateTime";

    @IgnoreField
    private static final long serialVersionUID = 1L;

    @Field(field = "id",comment = "主键",index = PRI.class,autoIncrement = true,allowNull = false)
    @Column(name = "id")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "name")
    @Field(field = "name",comment = "用户名")
    private String name;

    @Column(name = "account_id")
    @Field(field = "account_id",comment = "账号ID 关联user_account表")
    private Integer accountId;

    @Column(name = "gender")
    @Field(field = "gender",comment = "性别 0男 1女",defaultValue = "0")
    private Integer gender;

    @Column(name = "mobile")
    @Field(field = "mobile",comment = "手机号码")
    private String mobile;

    @Column(name = "email")
    @Field(field = "email",comment = "邮箱")
    private String email;

    @Column(name = "deleted")
    @Field(field = "deleted",defaultValue = "0",comment = "是否删除 0否 1是")
    private Byte deleted;

    @Column(name = "birthday")
    @Field(field = "birthday",comment = "出生日期")
    private Date birthday;

    @Column(name = "type")
    @Field(field = "type",comment = "用户类型")
    private Integer type;

    @Column(name = "professional")
    @Field(field = "professional",comment = "职称")
    private Integer professional;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    @Field(field = "create_time",comment = "更新时间")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time")
    @Field(field = "update_time",comment = "更新时间")
    private Date updateTime;

}
