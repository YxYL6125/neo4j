package com.liyao.neo4j;

import com.liyao.neo4j.eneity.*;
import com.liyao.neo4j.mapper.StudentMapper;
import com.liyao.neo4j.repository.node.BookRepository;
import com.liyao.neo4j.repository.node.CourseRepository;
import com.liyao.neo4j.repository.node.ExaminationRepository;
import com.liyao.neo4j.repository.node.GraduationDesignRepository;
import com.liyao.neo4j.repository.relationship.CreateGratualtionDesignTeacherRelation;
import com.liyao.neo4j.repository.relationship.CreateRelation;
import com.liyao.neo4j.repository.relationship.CreateStudentRelation;
import com.liyao.neo4j.repository.relationship.CreateTeacherRelation;
import com.liyao.neo4j.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
class Neo4jApplicationTests {
    @Resource
    GraduationDesignRepository graduationDesignRepository;
    @Resource
    CreateRelation createRelation;
    @Autowired
    GetNodeService getNodeService;
    @Autowired
    CreateStudentRelationShipService createStudentRelationShipService;
    @Autowired
    CreateTeacherRelationShipService createTeacherRelationShipService;
    @Autowired
    CreateGraduationDesignTeacherRelationShipService createGraduationDesignTeacherRelationShipService ;
    @Resource
    CreateAllNodeService createAllNodeService;
    @Resource
    CreateAllRelationShipService createAllRelationShipService;
    @Resource
    CreateStudentRelation createStudentRelation;
    @Resource
    CreateTeacherRelation createTeacherRelation;
    @Autowired
    CourseRepository courseRepository;
    @Resource
    StudentMapper studentMapper;
    @Test
    void InitRelationship() {
        createAllRelationShipService.InitRelationShip();
    }

    @Test
    void InitNodeAndRelationShip() {
        createAllNodeService.InitNode();
//        createAllRelationShipService.InitRelationShip();

//      学生
        Student student = getNodeService.getStudentById(2L);
        createStudentRelationShipService.InitStudentRelationShip(student);
//      老师
//        Teacher teacher = getNodeService.getTeacherList().get(0);
//        createTeacherRelationShipService.InitTeacherRelationShip(teacher);
        //毕业设计老师
//        GraduationDesignTeacher teacher = getNodeService.getGraduationDesignTeacherList().get(0);
//        createGraduationDesignTeacherRelationShipService.Init(teacher);

    }


    @Test
    void test1() {
        Student student = getNodeService.getStudentById(3L);
        student.setStudentName("张三");
        student.setAge(22);
        studentMapper.updateStudent(student);
    }
}
