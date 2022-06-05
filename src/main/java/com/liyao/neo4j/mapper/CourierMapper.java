package com.liyao.neo4j.mapper;

import com.liyao.neo4j.eneity.Courier;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourierMapper {

    @Select(value = "SELECT * FROM `courier`")
    public List<Courier> getCourierList();

    @Select(value = "SELECT * FROM `courier` WHERE buyer_id=#{id} and identity_code='0'")
    public List<Courier> getStudentCourierByBuyerId(long id);
    @Select(value = "SELECT * FROM `courier` WHERE buyer_id=#{id} and identity_code!='0'")
    public List<Courier> getTeacherCourierByBuyerId(long id);

}
