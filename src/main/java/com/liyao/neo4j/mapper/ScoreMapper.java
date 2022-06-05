package com.liyao.neo4j.mapper;

import com.liyao.neo4j.eneity.Score;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ScoreMapper {
    @Select(value = "select * from score where course_score is NOT NULL")
    List<Score> selectAllCourseScoreNotNull();

    @Select(value = "select * from score where student_id=#{id}")
    List<Score> selectAllScoreByStudentId(long id);
    @Select(value = "select * from score ")
    List<Score> selectAllScore();
}
