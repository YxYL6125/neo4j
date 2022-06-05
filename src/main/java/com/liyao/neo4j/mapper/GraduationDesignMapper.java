package com.liyao.neo4j.mapper;

import com.liyao.neo4j.eneity.GraduationDesign;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GraduationDesignMapper {
    @Select(value = "select * from graduation_design")
    List<GraduationDesign> selectAllGraduationDesign();
    @Select(value = "select *from graduation_design where id=#{id}")
    GraduationDesign selectById(Long id);
}
