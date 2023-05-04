package com.lsw.management.admin.model.dto.score;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/5/4  11:57
 */
@Data
public class ScoreAddDto {

    private Integer projectId;

    @NotEmpty(message = "CAN_NOT_EMPTY")
    private String opinion;

    private Integer result;

    private Double score;
}
