package com.liyao.neo4j.mapper;

import com.liyao.neo4j.eneity.Course;
import com.liyao.neo4j.eneity.Score;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourseMapper {
    @Select(value = "select * from course")
    List<Course> selectAllCourse();
}
