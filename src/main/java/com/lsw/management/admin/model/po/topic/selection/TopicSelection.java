package com.lsw.management.admin.model.po.topic.selection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lsw.management.common.util.table.sync.annotation.Field;
import com.lsw.management.common.util.table.sync.annotation.IgnoreField;
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
 * @TableName topic_selection
 */
@TableSync(tableName = "topic_selection",tableComment = "毕设出题表",delOldField = false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topic_selection")
public class TopicSelection implements Serializable {

    public static final String ID="id";
    public static final String TITLE="title";
    public static final String TEACHER="teacherId";
    public static final String MAJOR="major";
    public static final String STUDENT_TYPE="studentType";
    public static final String STUDENT_NUM="studentNum";
    public static final String CREATE_TIME="createTime";
    public static final String UPDATE_TIME="updateTime";
    public static final String DELETED="deleted";
    public static final String DIRECTION="direction";
    /**
     * 主键
     */
    @Field(field = "id",comment = "主键id",index = PRI.class,autoIncrement = true,allowNull = false)
    @Column(name = "id")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 毕设题目
     */
    @Field(field = "title",comment = "毕设题目")
    @Column(name = "title")
    private String title;

    /**
     * 出题老师
     */
    @Field(field = "teacher_id",comment = "出题老师 关联user_info表")
    @Column(name = "teacher_id")
    private Integer teacherId;

    /**
     * 适合专业
     */
    @Field(field = "major",comment = "适合专业")
    @Column(name = "major")
    private Integer major;

    /**
     * 学生类型 0本科生 1研究生 2博士生
     */
    @Field(field = "student_type",defaultValue = "0",comment = "学生类型 0本科生 1研究生 2博士生")
    @Column(name = "student_type")
    private Integer studentType;

    /**
     * 指导学生数
     */
    @Field(field = "student_num",defaultValue = "0",comment = "指导学生数")
    @Column(name = "student_num")
    private Integer studentNum;

    /**
     * 创建时间
     */
    @Field(field = "create_time",comment = "创建时间")
    @JsonFormat(pattern = TIME_PATTERN, timezone = "GMT+8")
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

    /**
     * 是否删除 0否 1是
     */
    @Field(field = "deleted",defaultValue = "0",comment = "是否删除 0否 1是")
    @Column(name = "deleted")
    private Byte deleted;

    /**
     * 研究方向
     */
    @Field(field = "direction",defaultValue = "0",comment = "研究方向")
    @Column(name = "direction")
    private String direction;

    @IgnoreField
    private static final long serialVersionUID = 1L;
}