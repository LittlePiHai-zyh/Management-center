package com.zhangyh.management.admin.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhangyh.management.common.util.excel.annotion.ExcelSelect;
import com.zhangyh.management.common.util.table.sync.annotation.Field;
import com.zhangyh.management.common.util.table.sync.annotation.TableSync;
import com.zhangyh.management.common.util.table.sync.sql.table.mysql.index.PRI;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangyh
 * @Date 2023/4/7 16:30
 * @desc
 */
@TableSync(tableComment = "日志表")
@Data
@Table(name = "sys_log")
public class SysLog  implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String ID="id";
    public static final String TITLE="level";
    public static final String BUSINESS_TYPE="businessType";
    public static final String REQUEST_METHOD="requestMethod";
    public static final String OPER_NAME="operName";
    public static final String OPER_URL="operUrl";
    public static final String OPER_IP="operIp";
    public static final String OPER_TIME="operTime";
    public static final String EXCEPTION_DETAIL="exceptionDetail";
    public static final String DELETED="deleted";
    public static final String CREATE_TIME="createTime";
    public static final String UPDATE_TIME="updateTime";

    /**
     * 日志主键
     */
    @Field(field = "id",index = PRI.class,allowNull = false,autoIncrement = true,comment = "主键")
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 操作模块
     */
    @ExcelSelect
    @Field(field = "level",comment = "日志级别")
    @Column(name = "level")
    private Integer level;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @Field(field = "business_type",comment = "业务类型")
    @Column(name = "business_type")
    private Integer businessType;

    /**
     * 请求方式
     */
    @Field(field = "request_method",comment = "请求方式")
    @Column(name = "request_method")
    private String requestMethod;

    /**
     * 操作人员
     */
    @Field(field = "oper_name",comment = "操作用户")
    @Column(name = "oper_name")
    private String operName;

    /**
     * 请求url
     */
    @Field(field = "oper_url",comment = "请求URL")
    @Column(name = "oper_url")
    private String operUrl;

    /**
     * 操作地址
     */
    @Field(field = "oper_ip",comment = "请求的用户IP")
    @Column(name = "oper_ip")
    private String operIp;

    /**
     * 操作时间
     */
    @Field(field = "oper_time",comment = "操作耗时")
    @Column(name = "oper_time")
    private Long operTime;

    @Field(field = "exception_detail",comment = "异常详情",length = 1024)
    @Column(name = "exception_detail")
    private String exceptionDetail;

    @Field(field = "deleted",defaultValue = "0",comment = "是否删除 0否 1是",length = 2)
    @Column(name = "deleted")
    private Integer deleted;

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
