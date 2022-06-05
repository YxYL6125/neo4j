package com.liyao.neo4j.mapper;

import com.liyao.neo4j.eneity.Examination;
import com.liyao.neo4j.eneity.GraduationConnection;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GraduationConnectionMapper {
    @Select(value = "select *from graduation_connection")
    List<GraduationConnection> selectAllGraduationConnection();
    @Select(value = "select *from graduation_connection where student_id=#{id}")
    GraduationConnection  selectAllGraduationConnectionById(Long id);
    @Select(value = "select *from graduation_connection where graduation_design_teacher_id=#{id}")
    List<GraduationConnection>  selectAllGraduationConnectionByTeacherId(Long id);
}
