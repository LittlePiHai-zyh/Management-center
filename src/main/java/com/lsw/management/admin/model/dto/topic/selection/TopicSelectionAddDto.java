package com.lsw.management.admin.model.dto.topic.selection;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/30  18:24
 */
@Data
public class TopicSelectionAddDto {

    @NotEmpty(message = "CAN_NOT_EMPTY")
    private String title;

    private Integer teacherId;

    private Integer major;

    private Integer studentType;

    private Integer studentNum;

    @NotEmpty(message = "CAN_NOT_EMPTY")
    private String direction;
}
