package com.liyao.neo4j.repository.node;

import com.liyao.neo4j.eneity.Course;
import com.liyao.neo4j.eneity.HomeWork;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface HomeWorkRepository extends Neo4jRepository<HomeWork,Long> {
    @Query("merge(n:homework{" +
            "id::#{#homeWork.id}," +
            "courseId::#{#homeWork.courseId}," +
            "homeworkName::#{#homeWork.homeworkName}," +
            "endTime::#{#homeWork.endTime}" +
            "})")
    void addHomeWorkNode(HomeWork homeWork);
}
