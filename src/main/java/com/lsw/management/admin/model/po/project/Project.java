package com.lsw.management.admin.model.po.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lsw.management.common.util.table.sync.annotation.Field;
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

import static com.lsw.management.common.constants.GlobalConstants.TIME_PATTERN;

/**
 * 
 * @TableName project
 */
@TableSync(tableName = "project",tableComment = "毕设状态跟踪表",delOldField = false)
@Data
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
    @Field(field = "status",comment = "毕业设计状态：0进行中、1已完成、2已取消等")
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
     * 指导教师 ID，关联user_account表 
     */
    @Field(field = "teacher_id",comment = "指导教师 ID，关联user_account表 ")
    @Column(name = "teacher_id")
    private Integer teacherId;

    /**
     * 是否删除 0否 1是 
     */
    @Field(field = "deleted",defaultValue = "0",comment = "是否删除 0否 1是")
    @Column(name = "deleted")
    private Integer deleted;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Project other = (Project) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTopicId() == null ? other.getTopicId() == null : this.getTopicId().equals(other.getTopicId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
            && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
            && (this.getTeacherId() == null ? other.getTeacherId() == null : this.getTeacherId().equals(other.getTeacherId()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTopicId() == null) ? 0 : getTopicId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getTeacherId() == null) ? 0 : getTeacherId().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", topicId=").append(topicId);
        sb.append(", status=").append(status);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", studentId=").append(studentId);
        sb.append(", teacherId=").append(teacherId);
        sb.append(", deleted=").append(deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}