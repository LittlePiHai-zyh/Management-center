package com.lsw.management.admin.model.po.announcement;

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
 * @TableName announcement
 */
@TableSync(tableName = "announcement",tableComment = "系统公告表",delOldField = false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "announcement")
public class Announcement implements Serializable {

    public static final String ID="id";
    public static final String TITTLE="title";
    public static final String CONTENT="content";
    public static final String ACCOUNT_ID="accountId";
    public static final String DELETED="deleted";
    public static final String CREATE_TIME="createTime";
    public static final String UPDATE_TIME="updateTime";

    /**
     * 主键
     */
    @Field(field = "id",comment = "主键id",index = PRI.class,autoIncrement = true,allowNull = false)
    @Column(name = "id")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 公告标题
     */
    @Field(field = "title",comment = "公告标题")
    @Column(name = "title")
    private String title;

    /**
     * 公告内容
     */
    @Field(field = "content",comment = "公告内容",length = 1024)
    @Column(name = "content")
    private String content;

    /**
     * 发布公告的账号---关联user_account表
     */
    @Field(field = "account_id",comment = "发布公告的账号---关联user_account表")
    @Column(name = "account_id")
    private Integer accountId;

    /**
     * 是否删除 0否 1是
     */
    @Field(field = "deleted",defaultValue = "0",comment = "是否删除 0否 1是")
    @Column(name = "deleted")
    private Integer deleted;

    /**
     * 创建时间
     */
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
        Announcement other = (Announcement) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", accountId=").append(accountId);
        sb.append(", deleted=").append(deleted);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}