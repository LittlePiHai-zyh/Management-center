package com.lsw.management.admin.mapper;

import com.lsw.management.admin.model.dto.score.ScoreQueryDto;
import com.lsw.management.admin.model.po.scores.ScoreVo;
import com.lsw.management.admin.model.po.scores.Scores;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
* @author 17533
* @description 针对表【scores(打分表)】的数据库操作Mapper
* @createDate 2023-05-04 11:52:48
* @Entity generator.domain.Scores
*/
public interface ScoresMapper extends Mapper<Scores> {

    List<ScoreVo> listAll(@Param("queryDto") ScoreQueryDto queryDto);
}
