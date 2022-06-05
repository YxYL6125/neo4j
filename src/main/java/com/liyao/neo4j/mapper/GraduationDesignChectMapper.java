package com.liyao.neo4j.mapper;

import com.liyao.neo4j.eneity.GraduationDesignChect;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GraduationDesignChectMapper {
    @Select(value = "SELECT * FROM graduation_design_chect where subject_result is not NULL")
    List<GraduationDesignChect> getGraduationDesignChectList();
}
