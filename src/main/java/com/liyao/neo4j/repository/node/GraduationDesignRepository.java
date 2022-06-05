package com.liyao.neo4j.repository.node;

import com.liyao.neo4j.eneity.GraduationDesign;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface GraduationDesignRepository extends Neo4jRepository<GraduationDesign,Long> {
    @Query("merge(n:graduationDesign{" +
            "id::#{#graduationDesign.id}," +
            "designReport::#{#graduationDesign.designReport}," +
            "designPaper::#{#graduationDesign.designPaper}," +
            "subject::#{#graduationDesign.subject}" +
            "})")
    void addGraduationDesignNode(GraduationDesign graduationDesign);
}
