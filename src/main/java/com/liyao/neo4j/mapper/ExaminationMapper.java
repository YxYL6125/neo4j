package com.liyao.neo4j.mapper;

import com.liyao.neo4j.eneity.Dormitory;
import com.liyao.neo4j.eneity.Examination;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ExaminationMapper {
    @Select(value = "select *from examination")
    List<Examination> selectAllExamination();
}
