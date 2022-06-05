package com.liyao.neo4j.repository.node;

import com.liyao.neo4j.eneity.Book;
import com.liyao.neo4j.eneity.Courier;
import com.liyao.neo4j.eneity.Course;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CourierRepository extends Neo4jRepository<Courier,Long> {
    @Query("merge(n:courier{" +
            "id::#{#courier.id}," +
            "goodsName::#{#courier.goodsName}," +
            "receiveAddress::#{#courier.receiveAddress}," +
            "buyerId::#{#courier.buyerId}," +
            "identityCode::#{#courier.identityCode}," +
            "arriveTime::#{#courier.arriveTime}" +
            "})")
    void addCourierNode(Courier courier);

}
