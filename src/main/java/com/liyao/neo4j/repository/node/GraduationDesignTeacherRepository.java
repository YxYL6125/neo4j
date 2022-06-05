package com.liyao.neo4j.repository.node;

import com.liyao.neo4j.eneity.Book;
import com.liyao.neo4j.eneity.Course;
import com.liyao.neo4j.eneity.GraduationDesignTeacher;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface GraduationDesignTeacherRepository extends Neo4jRepository<GraduationDesignTeacher,Long> {
    @Query("merge(n:graduation_design_teacher{" +
            "id::#{#graduationDesignTeacher.id}," +
            "graduationDesignTeacherName::#{#graduationDesignTeacher.graduationDesignTeacherName}," +
            "graduationDesignTeacherTelephone::#{#graduationDesignTeacher.graduationDesignTeacherTelephone}" +
            "})")
    void addGraduationDesignTeacherNode(GraduationDesignTeacher graduationDesignTeacher);
}
