package com.liyao.neo4j.repository.node;

import com.liyao.neo4j.eneity.Dormitory;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface DormitoryRepository extends Neo4jRepository<Dormitory,Long> {
    @Query("merge(n:dormitory{" +
            "id::#{#dormitory.id}," +
            "bulidName::#{#dormitory.bulidName}," +
            "floor::#{#dormitory.floor}," +
            "room::#{#dormitory.room}" +
            "})")
    void addDormitoryNode(Dormitory dormitory);
}
