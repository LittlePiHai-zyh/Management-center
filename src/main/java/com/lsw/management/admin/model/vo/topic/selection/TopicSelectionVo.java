package com.lsw.management.admin.model.vo.topic.selection;

import lombok.Data;

import java.util.Date;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/30  18:25
 */
@Data
public class TopicSelectionVo {

    private Integer id;

    private String title;

    private String teacherName;

    private String major;

    private String studentType;

    private Integer studentNum;

    private Date createTime;

    private String direction;

    private Integer state;
}
