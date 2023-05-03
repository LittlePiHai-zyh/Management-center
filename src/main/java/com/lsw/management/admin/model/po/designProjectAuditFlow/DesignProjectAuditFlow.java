package com.lsw.management.admin.model.po.designProjectAuditFlow;

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

import static com.lsw.management.common.constants.GlobalConstants.TIME_PATTERN;

/**
 * 
 * @TableName design_project_audit_flow
 */
@TableSync(tableName = "design_project_audit_flow",tableComment = "毕设出题表",delOldField = false)
@Data
@Table(name = "design_project_audit_flow")
public class DesignProjectAuditFlow implements Serializable {

    public static final String ID="id";
    public static final String DEPARTMENT_AUDIT_RESULT="departmentAuditResult";
    public static final String DEPARTMENT_AUDIT_USER_ID="departmentAuditUserId";
    public static final String SCHOOL_AUDIT_USER_ID="schoolAuditUserId";
    public static final String SCHOOL_AUDIT_TIME="schoolAuditTime";
    public static final String SCHOOL_AUDIT_RESULT="schoolAuditResult";
    public static final String STATE="state";
    public static final String DEPARTMENT_AUDIT_TIME="departmentAuditTime";
    public static final String TOPIC_ID="topicId";
    public static final String DEPARTMENT_AUDIT_OPINION="departmentAuditOpinion";
    public static final String SCHOOL_AUDIT_USER_OPINION="schoolAuditUserOpinion";
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
     * 系负责人审核结果 0驳回 1通过
     */
    @Field(field = "department_audit_result",comment = "系负责人审核结果 0驳回 1通过")
    @Column(name = "department_audit_result")
    private Integer departmentAuditResult;

    /**
     * 系负责人审核的用户ID
     */
    @Field(field = "department_audit_user_id",comment = "系负责人审核的用户ID，关联user_account表id")
    @Column(name = "department_audit_user_id")
    private Integer departmentAuditUserId;

    /**
     * 院负责人审核的用户ID
     */
    @Field(field = "school_audit_user_id",comment = "院负责人审核的用户ID,关联user_account表id")
    @Column(name = "school_audit_user_id")
    private Integer schoolAuditUserId;

    /**
     * 院级审核时间
     */
    @Field(field = "school_audit_time",comment = "院级审核时间")
    @Column(name = "school_audit_time")
    @JsonFormat(pattern =TIME_PATTERN, timezone = "GMT+8")
    @DateTimeFormat(pattern = TIME_PATTERN)
    private Date schoolAuditTime;

    /**
     * 院负责人审核结果 0驳回 1通过
     */
    @Field(field = "school_audit_result",comment = "院负责人审核结果 0驳回 1通过")
    @Column(name = "school_audit_result")
    private Integer schoolAuditResult;

    /**
     * 当前审计状态，“0 未审核”、“1 系已审核”、“2 院已审核”等
     */
    @Field(field = "state",comment = "当前审计状态，“0 未审核”、“1 系已审核”、“2 院已审核” 、“3已选题”等")
    @Column(name = "state")
    private Integer state;

    @Field(field = "deleted",defaultValue = "0",comment = "是否删除 0否 1是")
    @Column(name = "deleted")
    private Byte deleted;

    /**
     * 系别审核时间
     */
    @Field(field = "department_audit_time",comment = "系别审核时间")
    @Column(name = "department_audit_time")
    @JsonFormat(pattern =TIME_PATTERN, timezone = "GMT+8")
    @DateTimeFormat(pattern = TIME_PATTERN)
    private Date departmentAuditTime;

    /**
     * "毕设题目 关联topic_selection表的id
     */
    @Field(field = "topic_id",comment = "毕设题目 关联topic_selection表的id")
    @Column(name = "topic_id")
    private Integer topicId;

    /**
     * 系负责人审核备注
     */
    @Field(field = "department_audit_opinion",comment = "系负责人审核备注")
    @Column(name = "department_audit_opinion")
    private String departmentAuditOpinion;

    @Field(field = "no_answer_opinion",comment = "免答审核备注")
    @Column(name = "no_answer_opinion")
    private String noAnswerOpinion;

    /**
     * 院负责人审核备注
     */
    @Field(field = "school_audit_user_opinion",comment = "院负责人审核备注")
    @Column(name = "school_audit_user_opinion")
    private String schoolAuditUserOpinion;

    @IgnoreField
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
        DesignProjectAuditFlow other = (DesignProjectAuditFlow) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDepartmentAuditResult() == null ? other.getDepartmentAuditResult() == null : this.getDepartmentAuditResult().equals(other.getDepartmentAuditResult()))
            && (this.getDepartmentAuditUserId() == null ? other.getDepartmentAuditUserId() == null : this.getDepartmentAuditUserId().equals(other.getDepartmentAuditUserId()))
            && (this.getSchoolAuditUserId() == null ? other.getSchoolAuditUserId() == null : this.getSchoolAuditUserId().equals(other.getSchoolAuditUserId()))
            && (this.getSchoolAuditTime() == null ? other.getSchoolAuditTime() == null : this.getSchoolAuditTime().equals(other.getSchoolAuditTime()))
            && (this.getSchoolAuditResult() == null ? other.getSchoolAuditResult() == null : this.getSchoolAuditResult().equals(other.getSchoolAuditResult()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getDepartmentAuditTime() == null ? other.getDepartmentAuditTime() == null : this.getDepartmentAuditTime().equals(other.getDepartmentAuditTime()))
            && (this.getTopicId() == null ? other.getTopicId() == null : this.getTopicId().equals(other.getTopicId()))
            && (this.getDepartmentAuditOpinion() == null ? other.getDepartmentAuditOpinion() == null : this.getDepartmentAuditOpinion().equals(other.getDepartmentAuditOpinion()))
            && (this.getSchoolAuditUserOpinion() == null ? other.getSchoolAuditUserOpinion() == null : this.getSchoolAuditUserOpinion().equals(other.getSchoolAuditUserOpinion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDepartmentAuditResult() == null) ? 0 : getDepartmentAuditResult().hashCode());
        result = prime * result + ((getDepartmentAuditUserId() == null) ? 0 : getDepartmentAuditUserId().hashCode());
        result = prime * result + ((getSchoolAuditUserId() == null) ? 0 : getSchoolAuditUserId().hashCode());
        result = prime * result + ((getSchoolAuditTime() == null) ? 0 : getSchoolAuditTime().hashCode());
        result = prime * result + ((getSchoolAuditResult() == null) ? 0 : getSchoolAuditResult().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getDepartmentAuditTime() == null) ? 0 : getDepartmentAuditTime().hashCode());
        result = prime * result + ((getTopicId() == null) ? 0 : getTopicId().hashCode());
        result = prime * result + ((getDepartmentAuditOpinion() == null) ? 0 : getDepartmentAuditOpinion().hashCode());
        result = prime * result + ((getSchoolAuditUserOpinion() == null) ? 0 : getSchoolAuditUserOpinion().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", departmentAuditResult=").append(departmentAuditResult);
        sb.append(", departmentAuditUserId=").append(departmentAuditUserId);
        sb.append(", schoolAuditUserId=").append(schoolAuditUserId);
        sb.append(", schoolAuditTime=").append(schoolAuditTime);
        sb.append(", schoolAuditResult=").append(schoolAuditResult);
        sb.append(", state=").append(state);
        sb.append(", departmentAuditTime=").append(departmentAuditTime);
        sb.append(", topicId=").append(topicId);
        sb.append(", departmentAuditOpinion=").append(departmentAuditOpinion);
        sb.append(", schoolAuditUserOpinion=").append(schoolAuditUserOpinion);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}