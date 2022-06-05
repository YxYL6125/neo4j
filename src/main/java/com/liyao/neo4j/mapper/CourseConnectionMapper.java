package com.liyao.neo4j.mapper;

import com.liyao.neo4j.eneity.CourseConnection;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourseConnectionMapper {
    @Select(value = "select * from course_connection")
    List<CourseConnection> selectAllCourseConnection();
    @Select(value = "select * from course_connection where teacher_id=#{id}")
    List<CourseConnection> selectCourseConnectionByTeacherID(long id);
}
