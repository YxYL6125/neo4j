package com.liyao.neo4j.repository.node;

import com.liyao.neo4j.eneity.Course;
import com.liyao.neo4j.eneity.Teacher;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface TeacherRepository extends Neo4jRepository<Teacher,Long> {
    @Query("merge(n:teacher{" +
            "id::#{#teacher.id}," +
            "teacherName::#{#teacher.teacherName}," +
            "teacherTelephone::#{#teacher.teacherTelephone}" +
            "})")
    void addTeacherNode(Teacher teacher);
}
