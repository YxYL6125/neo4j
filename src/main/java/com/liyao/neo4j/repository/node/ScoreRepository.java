package com.liyao.neo4j.repository.node;

import com.liyao.neo4j.eneity.Dormitory;
import com.liyao.neo4j.eneity.Score;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ScoreRepository extends Neo4jRepository<Score,Long> {
    @Query("merge(n:score{" +
            "id::#{#score.id}," +
            "courseId::#{#score.courseId}," +
            "studentId::#{#score.studentId}," +
            "courseScore::#{#score.courseScore}" +
            "})")
    void addScoreNode(Score score);
}
