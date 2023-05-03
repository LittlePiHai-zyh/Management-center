package com.lsw.management.admin.model.dto.project;

import lombok.Data;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/5/3  17:47
 */
@Data
public class ProjectNoAnswerAddDto {

    private Integer id;

    private Integer topicId;

    private String noAnswerOpinion;

    private Integer agree;
}
