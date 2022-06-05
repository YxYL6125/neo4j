package com.liyao.neo4j.mapper;

import com.liyao.neo4j.eneity.Course;
import com.liyao.neo4j.eneity.Deliveryman;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DeliveryManMapper {
    @Select(value = "select * from deliveryman")
    List<Deliveryman> selectAllDeliverMan();
}
