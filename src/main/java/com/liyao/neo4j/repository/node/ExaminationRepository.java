package com.liyao.neo4j.repository.node;

import com.liyao.neo4j.eneity.Dormitory;
import com.liyao.neo4j.eneity.Examination;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ExaminationRepository extends Neo4jRepository<Examination,Long> {
    @Query("merge(n:examination{" +
            "id::#{#examination.id}," +
            "courseId::#{#examination.courseId}," +
            "examinationClassroom::#{#examination.examinationClassroom}," +
            "startTime::#{#examination.startTime}," +
            "endTime::#{#examination.endTime}" +
            "})")
    void addExaminationNode(Examination examination);
}
