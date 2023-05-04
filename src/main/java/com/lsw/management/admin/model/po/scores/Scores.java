package com.lsw.management.admin.model.po.scores;

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
 * @Author: lsw
 * @desc 教师评分
 * @date: 2023/5/4  11:38
 */
@TableSync(tableName = "scores",tableComment = "打分表",delOldField = false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "scores")
public class Scores  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field(field = "id",comment = "主键id",index = PRI.class,autoIncrement = true,allowNull = false)
    @Column(name = "id")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Field(field = "project_id",comment = "毕设 关联Project表")
    @Column(name = "project_id")
    private Integer projectId;

    @Field(field = "opinion",comment = "评审指标")
    @Column(name = "opinion")
    private String opinion;

    @Field(field = "result",comment = "评审结果")
    @Column(name = "result")
    private Integer result;

    @Field(field = "score",comment = "毕设分数")
    @Column(name = "score")
    private Double score;

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
}
