package com.lsw.management.admin.model.po.file;

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
import java.util.Date;

import static com.lsw.management.common.constants.GlobalConstants.TIME_PATTERN;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/5/4  19:40
 */
@TableSync(tableName = "file",tableComment = "文件管理",delOldField = false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "file")
public class File {

    @Field(field = "id",comment = "主键id",index = PRI.class,autoIncrement = true,allowNull = false)
    @Column(name = "id")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Field(field = "file_name",comment = "文件名 ")
    @Column(name = "file_name")
    private String fileName;

    @Field(field = "account_id",comment = "上传用户 ")
    @Column(name = "account_id")
    private Integer accountId;

    /**
     * 是都删除 0否1是
     */
    @Field(field = "deleted",defaultValue = "0",comment = "是否删除 0否 1是")
    @Column(name = "deleted")
    private Byte deleted;

    @Field(field = "create_time",comment = "创建时间")
    @JsonFormat(pattern =TIME_PATTERN, timezone = "GMT+8")
    @DateTimeFormat(pattern = TIME_PATTERN)
    @Column(name = "create_time")
    private Date createTime;
}
