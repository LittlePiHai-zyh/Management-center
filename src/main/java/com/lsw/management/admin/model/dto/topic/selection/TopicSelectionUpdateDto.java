package com.lsw.management.admin.model.dto.topic.selection;

import lombok.Data;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/30  18:24
 */
@Data
public class TopicSelectionUpdateDto {

    private Integer id;

    private String title;

    private Integer teacherId;

    private Integer major;

    private Integer studentType;

    private Integer studentNum;

    private String direction;
}
