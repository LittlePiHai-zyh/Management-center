package com.lsw.management.admin.service;

import com.lsw.management.admin.model.dto.score.ScoreAddDto;
import com.lsw.management.admin.model.dto.score.ScoreQueryDto;
import com.lsw.management.admin.model.po.scores.ScoreVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/5/4  11:55
 */
public interface ScoreService {
    Integer add(ScoreAddDto score, HttpServletRequest request);

    List<ScoreVo> listAll(ScoreQueryDto queryDto);
}
