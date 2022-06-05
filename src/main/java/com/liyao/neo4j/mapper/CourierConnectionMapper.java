package com.liyao.neo4j.mapper;

import com.liyao.neo4j.eneity.CourierConnection;
import com.liyao.neo4j.eneity.CourseConnection;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourierConnectionMapper {
    @Select(value = "select * from courier_connection")
    public List<CourierConnection> selectAllCourierConnection();
}
