package com.liyao.neo4j.mapper;

import com.liyao.neo4j.eneity.Dormitory;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DormitoryMapper {
    @Select(value = "select * from dormitory")
    List<Dormitory> selectAllDormitory();
}
