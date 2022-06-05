package com.liyao.neo4j.mapper;

import com.liyao.neo4j.eneity.Book;
import com.liyao.neo4j.eneity.GraduationDesignTeacher;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GraduationDesignTeacherMapper {
    @Select(value = "select * from graduation_design_teacher")
    List<GraduationDesignTeacher> selectAllGraduationDesignTeacher();
}
