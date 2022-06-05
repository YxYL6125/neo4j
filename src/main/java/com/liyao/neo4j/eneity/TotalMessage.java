package com.liyao.neo4j.eneity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotalMessage {
    //student
    private String speciality;
    private long cardNumber;
    private String sex;
    private String studentName;
    private long age;
    private String college;
    //dormitory
    private String bulidName;
    private String floor;
    private String room;
    //course
    private String courseName;
    private String courseClassroom;
    //score
    private long courseScore;
    //examination
    private String examinationClassroom;
    private java.sql.Timestamp startTime;
    private java.sql.Timestamp endTime;
    //teacher
    private String teacherName;
    private String teacherTelephone;
    //gradduationDesign
    private String subject;
    private String graduationDesignTeacherName;
    private String graduationDesignTeacherTelephone;
    //book
    private String bookName;
    private String bookAuthor;
    private String publishYear;

}
