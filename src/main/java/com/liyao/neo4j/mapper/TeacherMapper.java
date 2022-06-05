package com.liyao.neo4j.mapper;

import com.liyao.neo4j.eneity.Course;
import com.liyao.neo4j.eneity.Teacher;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

public interface TeacherMapper {
    @Select(value = "select * from teacher")
    List<Teacher> selectAllTeacher();

    @Select(value = "select * from teacher where id=#{id}")
    Teacher selectTeacherById(long id);

    @Update(value = "update teacher set teacher_name=#{teacherName}, " +
            "teacher_telephone=#{teacherTelephone} where id=#{id} ")
    Integer updateTeacher(Teacher teacher);
}
