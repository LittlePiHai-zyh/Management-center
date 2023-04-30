package com.lsw.management.admin.model.po.menu;

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
 * @TableName menu
 */
@TableSync(tableName = "menu",tableComment = "毕设出题表",delOldField = false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu")
public class Menu implements Serializable {

    public static final String MENU_ID="menuId";
    public static final String PID="pid";
    public static final String TITTLE="title";
    public static final String ICON="icon";
    public static final String ICON_COLOR="iconColor";
    public static final String PATH="path";
    public static final String NAME="name";
    public static final String HIDDEN="hidden";
    public static final String CREATE_BY="createBy";
    public static final String UPDATE_BY="updateBy";
    public static final String CREATE_TIME="createTime";
    public static final String UPDATE_TIME="updateTime";
    public static final String PERMISSIONS="permissions";
    public static final String DELETED="deleted";
    public static final String COMPONENT="component";

    /**
     * 主键
     */
    @Field(field = "menu_id",comment = "主键id",index = PRI.class,autoIncrement = true,allowNull = false)
    @Column(name = "menu_id")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer menuId;

    /**
     * 上级菜单ID
     */
    @Field(field = "pid",comment = "上级菜单ID")
    @Column(name = "pid")
    private Integer pid;

    /**
     * 菜单标题
     */
    @Field(field = "title",comment = "菜单标题")
    @Column(name = "title")
    private String title;

    /**
     * 菜单图标
     */
    @Field(field = "icon",comment = "菜单图标")
    @Column(name = "icon")
    private String icon;

    /**
     * 菜单图标颜色
     */
    @Field(field = "icon_color",comment = "菜单图标颜色")
    @Column(name = "icon_color")
    private String iconColor;

    /**
     * 路由
     */
    @Field(field = "path",comment = " 路由")
    @Column(name = "path")
    private String path;

    /**
     * 组件名字
     */
    @Field(field = "name",comment = "组件名字")
    @Column(name = "name")
    private String name;

    /**
     * 是否隐藏 0否 1是
     */
    @Field(field = "hidden",comment = "是否隐藏 0否 1是 ")
    @Column(name = "hidden")
    private Integer hidden;

    /**
     * 创建者
     */
    @Field(field = "create_by",comment = "创建者")
    @Column(name = "create_by")
    private String createBy;

    /**
     * 更新者
     */
    @Field(field = "update_by",comment = "更新者 ")
    @Column(name = "update_by")
    private String updateBy;

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

    /**
     * 是都删除 0否1是
     */
    @Field(field = "deleted",defaultValue = "0",comment = "是否删除 0否 1是")
    @Column(name = "deleted")
    private Byte deleted;

    /**
     * 允许访问的权限
     */
    @Field(field = "permissions",comment = "允许访问的权限 ")
    @Column(name = "permissions")
    private String permissions;

    /**
     * 组件
     */
    @Field(field = "component",comment = "组件 ")
    @Column(name = "component")
    private String component;

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
        Menu other = (Menu) that;
        return (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals(other.getMenuId()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
            && (this.getIconColor() == null ? other.getIconColor() == null : this.getIconColor().equals(other.getIconColor()))
            && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getHidden() == null ? other.getHidden() == null : this.getHidden().equals(other.getHidden()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getPermissions() == null ? other.getPermissions() == null : this.getPermissions().equals(other.getPermissions()))
            && (this.getComponent() == null ? other.getComponent() == null : this.getComponent().equals(other.getComponent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMenuId() == null) ? 0 : getMenuId().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getIconColor() == null) ? 0 : getIconColor().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getHidden() == null) ? 0 : getHidden().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getPermissions() == null) ? 0 : getPermissions().hashCode());
        result = prime * result + ((getComponent() == null) ? 0 : getComponent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuId=").append(menuId);
        sb.append(", pid=").append(pid);
        sb.append(", title=").append(title);
        sb.append(", icon=").append(icon);
        sb.append(", iconColor=").append(iconColor);
        sb.append(", path=").append(path);
        sb.append(", name=").append(name);
        sb.append(", hidden=").append(hidden);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", deleted=").append(deleted);
        sb.append(", permissions=").append(permissions);
        sb.append(", component=").append(component);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}