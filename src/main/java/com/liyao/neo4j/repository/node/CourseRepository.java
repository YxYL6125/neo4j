package com.liyao.neo4j.repository.node;

import com.liyao.neo4j.eneity.Course;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface CourseRepository extends Neo4jRepository<Course,Long> {
    @Query("merge(n:course{" +
            "id::#{#course.id}," +
            "courseName::#{#course.courseName}," +
            "courseClassroom::#{#course.courseClassroom}" +
            "})")
    void addCourseNode(Course course);
    @Query("MATCH (c:course) RETURN c")
    List<Course> findCourseNode();
}
