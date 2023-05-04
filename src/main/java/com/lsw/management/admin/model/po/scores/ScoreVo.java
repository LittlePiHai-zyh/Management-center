package com.lsw.management.admin.model.po.scores;

import lombok.Data;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/5/4  12:00
 */
@Data
public class ScoreVo {

    private String opinion;

    private Integer result;

    private Double score;

    private String studentName;

    private String teacherName;

    private String title;

    private Integer major;
}
