package com.lsw.management.admin.model.po.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lsw.management.common.util.table.sync.annotation.Field;
import com.lsw.management.common.util.table.sync.annotation.TableSync;
import com.lsw.management.common.util.table.sync.sql.table.mysql.index.PRI;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

import static com.lsw.management.common.constants.GlobalConstants.TIME_PATTERN;

/**
 * 
 * @TableName project
 */
@TableSync(tableName = "project",tableComment = "毕设状态跟踪表",delOldField = false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project")
public class Project implements Serializable {

    public static final String ID="id";
    public static final String TOPIC_ID="topicId";
    public static final String STATUS="status";
    public static final String START_DATE="startDate";
    public static final String END_TIME="endDate";
    public static final String STUDENT_ID="studentId";
    public static final String TEACHER_ID="teacherId";
    public static final String DELETED="deleted";

    /**
     * 主键
     */
    @Field(field = "id",comment = "主键id",index = PRI.class,autoIncrement = true,allowNull = false)
    @Column(name = "id")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 关联topic_selection表 id 
     */
    @Field(field = "topic_id",comment = "毕设题目")
    @Column(name = "topic_id")
    private Integer topicId;

    /**
     * 毕业设计状态：0进行中、1已完成、2已取消等 
     */
    @Field(field = "status",comment = "毕业设计状态：0未选题、1进行中、2已完成，3已取消等")
    @Column(name = "status")
    private Integer status;

    /**
     * 毕业设计开始日期
     */
    @Field(field = "start_date",comment = "毕业设计开始日期")
    @Column(name = "start_date")
    @JsonFormat(pattern =TIME_PATTERN, timezone = "GMT+8")
    @DateTimeFormat(pattern = TIME_PATTERN)
    private Date startDate;

    /**
     * 毕业设计结束日期
     */
    @Field(field = "end_date",comment = "毕业设计结束日期")
    @Column(name = "end_date")
    @JsonFormat(pattern =TIME_PATTERN, timezone = "GMT+8")
    @DateTimeFormat(pattern = TIME_PATTERN)
    private Date endDate;

    /**
     * 学生 ID，关联user_account表 
     */
    @Field(field = "student_id",comment = "学生 ID，关联user_account表 ")
    @Column(name = "student_id")
    private Integer studentId;

    /**
     * 是否删除 0否 1是 
     */
    @Field(field = "deleted",defaultValue = "0",comment = "是否删除 0否 1是")
    @Column(name = "deleted")
    private Integer deleted;

    @Field(field = "create_time",comment = "创建时间")
    @JsonFormat(pattern =TIME_PATTERN, timezone = "GMT+8")
    @DateTimeFormat(pattern = TIME_PATTERN)
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Field(field = "update_time",comment = "更新时间")
    @JsonFormat(pattern =TIME_PATTERN, timezone = "GMT+8")
    @DateTimeFormat(pattern = TIME_PATTERN)
    @Column(name = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}