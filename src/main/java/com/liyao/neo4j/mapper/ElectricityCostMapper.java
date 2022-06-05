package com.liyao.neo4j.mapper;

import com.liyao.neo4j.eneity.ElectricityCost;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ElectricityCostMapper {
    @Select(value = "select *from electricity_cost ")
    List<ElectricityCost> selectAllElectricityCost();
    @Select(value = "select *from electricity_cost where dormitory_id=#{id}")
    ElectricityCost selectElectricityCostByDormitoryId(long id);
}
