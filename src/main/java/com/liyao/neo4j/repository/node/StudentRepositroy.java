package com.liyao.neo4j.repository.node;

import com.liyao.neo4j.eneity.Book;
import com.liyao.neo4j.eneity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.transaction.annotation.Transactional;

public interface StudentRepositroy extends Neo4jRepository<Book, Long> {
    @Query("merge(n:student{studentName::#{#student.studentName}," +
            "id::#{#student.id}," +
            "age::#{#student.age}," +
            "sex::#{#student.sex}," +
            "speciality::#{#student.speciality}," +
            "cardNumber::#{#student.cardNumber}," +
            "college::#{#student.college}," +
            "dormitoryId::#{#student.dormitoryId}" +
            "})")
    void addStudentNode(Student student);
//    student_name,sex,speciality,card_number,age,college,
}
