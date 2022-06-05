package com.liyao.neo4j.service;

import com.liyao.neo4j.eneity.*;
import com.liyao.neo4j.repository.node.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 创建所有节点
 */
@Service
public class CreateAllNodeService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    DormitoryRepository dormitoryRepository;
    @Autowired
    ExaminationRepository examinationRepository;
    @Autowired
    GraduationDesignRepository graduationDesignRepository;
    @Autowired
    GraduationDesignTeacherRepository graduationDesignTeacherRepository;
    @Autowired
    ScoreRepository scoreRepository;
    @Autowired
    StudentRepositroy studentRepositroy;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    GetNodeService getNodeService;
    @Autowired
    HomeWorkRepository homeWorkRepository;
    @Autowired
    MeetingRepository meetingRepository;
    @Autowired
    ElectricityCostRepository electricityCostRepository;
    @Autowired
    CourierRepository courierRepository;
    @Autowired
    DeliverManRepository deliverManRepository;
    //初始化所有节点
    public void InitNode() {
        Date date = new Date();
        List<Book> bookList = getNodeService.GetBookList();
        for (Book book : bookList) {
            bookRepository.addBookNode(book);
        }
        List<Course> courses = getNodeService.getCourseList();
        for (Course course : courses) {
            courseRepository.addCourseNode(course);
        }
        List<Dormitory> dormitories = getNodeService.getDormitoryList();
        for (Dormitory dormitory : dormitories) {
            dormitoryRepository.addDormitoryNode(dormitory);
        }

        List<Examination> examinations = getNodeService.getExaminationList();
        for (Examination examination : examinations) {
            //判定条件:考试时间距今还有14天（>0,<=14）,才通知提示
            if ((examination.getStartTime().getTime() - date.getTime()) / (1000 * 60 * 60 * 24) <= 14
                    && (examination.getStartTime().getTime() - date.getTime()) / (1000 * 60 * 60 * 24)>0) {
                examinationRepository.addExaminationNode(examination);
            }
        }
        List<GraduationDesignTeacher> graduationDesignTeachers = getNodeService.getGraduationDesignTeacherList();
        for (GraduationDesignTeacher graduationDesignTeacher : graduationDesignTeachers) {
            graduationDesignTeacherRepository.addGraduationDesignTeacherNode(graduationDesignTeacher);
        }
        List<GraduationDesign> graduationDesigns = getNodeService.getGraduationDesignList();
        for (GraduationDesign graduationDesign : graduationDesigns) {
            graduationDesignRepository.addGraduationDesignNode(graduationDesign);
        }
        List<Score> scores = getNodeService.getCourseScoreNotNullList();
        for (Score score : scores) {
            scoreRepository.addScoreNode(score);
        }
        List<Student> students = getNodeService.getStudentList();
        for (Student student : students) {
            studentRepositroy.addStudentNode(student);
        }
        List<Teacher> teachers = getNodeService.getTeacherList();
        for (Teacher teacher : teachers) {
            teacherRepository.addTeacherNode(teacher);
        }
        List<HomeWork> homeWorks = getNodeService.getHomeWorkList();
        for (HomeWork homeWork : homeWorks) {
            homeWorkRepository.addHomeWorkNode(homeWork);
        }
        List<Meeting> meetings = getNodeService.getMeetingList();
        for (Meeting meeting : meetings) {
            meetingRepository.addMeetingNode(meeting);
        }
        List<ElectricityCost> electricityCosts = getNodeService.getElectricityCostList();
        for (ElectricityCost electricityCost : electricityCosts) {
            electricityCostRepository.addElectricityCostNode(electricityCost);
        }
        List<Courier> couriers = getNodeService.getAllCourier();
        for (Courier courier : couriers) {
            courierRepository.addCourierNode(courier);
        }
        List<Deliveryman> deliverymen = getNodeService.getDeliverymanList();
        for (Deliveryman deliveryman : deliverymen) {
            deliverManRepository.addDeliveryNode(deliveryman);
        }
    }




}
