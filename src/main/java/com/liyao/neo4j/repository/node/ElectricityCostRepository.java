package com.liyao.neo4j.repository.node;

import com.liyao.neo4j.eneity.Dormitory;
import com.liyao.neo4j.eneity.ElectricityCost;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ElectricityCostRepository extends Neo4jRepository<ElectricityCost, Long> {
    @Query("merge(n:electricityCost{" +
            "id::#{#electricityCost.id}," +
            "dormitoryId::#{#electricityCost.dormitoryId}," +
            "electricityStart::#{#electricityCost.electricityStart}," +
            "electricityEnd::#{#electricityCost.electricityEnd}," +
            "electricityPrice::#{#electricityCost.electricityPrice}" +
            "})")
    void addElectricityCostNode(ElectricityCost electricityCost);
}
