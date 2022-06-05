package com.liyao.neo4j.repository.node;

import com.liyao.neo4j.eneity.Course;
import com.liyao.neo4j.eneity.Deliveryman;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface DeliverManRepository extends Neo4jRepository<Deliveryman,Long> {
    @Query("merge(n:deliveryman{" +
            "id::#{#deliveryman.id}," +
            "name::#{#deliveryman.name}," +
            "telephone::#{#deliveryman.telephone}" +
            "})")
    void addDeliveryNode(Deliveryman deliveryman);
}
