package com.liyao.neo4j.mapper;

import com.liyao.neo4j.eneity.Student;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

public interface StudentMapper {

    List<Student> getStudent();

    @Select(value = "select *from student where id=#{id}")
    Student getStudentById(Long id);

    @Select(value = "UPDATE student set student_name=#{studentName},age=#{age},sex=#{sex}" +
            ", speciality=#{speciality},card_number=#{cardNumber},college=#{college}" +
            ",dormitory_id=#{dormitoryId} WHERE id=#{id}")
    Integer updateStudent(Student student);


    @Insert(value = "insert into student (id,student_name,age ,sex,speciality,card_number,college,dormitory_id) values (#{id},#{studentName},#{age}," +
            "#{sex},#{speciality}," +
            "#{cardNumber},#{college},#{dormitoryId}) ")
    void addStudent(Student student);

    @Delete(value = " DELETE FROM student WHERE id = #{id}")
    void deleteById(long id);
}