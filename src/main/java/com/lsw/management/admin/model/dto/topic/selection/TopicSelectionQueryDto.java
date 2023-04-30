package com.lsw.management.admin.model.dto.topic.selection;

import com.lsw.management.admin.model.dto.PageRequest;
import lombok.Data;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/30  18:24
 */
@Data
public class TopicSelectionQueryDto extends PageRequest {

    private String title;

    private String teacher;

    private Integer major;

    private Integer studentType;

    private String direction;
}
