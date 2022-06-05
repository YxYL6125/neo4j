package com.liyao.neo4j.service;

import com.liyao.neo4j.eneity.*;
import com.liyao.neo4j.mapper.*;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GetNodeService  {
    @Resource
    BookLendMapper bookLendMapper;
    @Resource
    BookMapper bookMapper;
    @Resource
    CourseConnectionMapper courseConnectionMapper;
    @Resource
    CourseMapper courseMapper;
    @Resource
    DormitoryMapper dormitoryMapper;
    @Resource
    ExaminationMapper examinationMapper;
    @Resource
    GraduationConnectionMapper graduationConnectionMapper;
    @Resource
    GraduationDesignMapper graduationDesignMapper;
    @Resource
    GraduationDesignTeacherMapper graduationDesignTeacherMapper;
    @Resource
    ScoreMapper scoreMapper;
    @Resource
    StudentMapper studentMapper;
    @Resource
    TeacherMapper teacherMapper;
    @Resource
    GraduationDesignChectMapper graduationDesignChectMapper;
    @Resource
    HomeWorkMapper homeWorkMapper;
    @Resource
    HomeWorkFinishmapper homeWorkFinishmapper;
    @Resource
    MeetingMapper meetingMapper;
    @Resource
    MeetingConnectionMapper meetingConnectionMapper;
    @Resource
    ElectricityCostMapper electricityCostMapper;
    @Resource
    CourierMapper courierMapper;
    @Resource
    DeliveryManMapper deliveryManMapper;
    @Resource
    CourierConnectionMapper courierConnectionMapper;

    public List<CourierConnection> getCourierConnection() {
        return courierConnectionMapper.selectAllCourierConnection();
    }
    public List<Deliveryman> getDeliverymanList() {
        return deliveryManMapper.selectAllDeliverMan();
    }
    public List<Courier> getAllCourier() {
        return courierMapper.getCourierList();
    }
    public List<Courier> getStudentCourierByBuyerId(long id) {
        return courierMapper.getStudentCourierByBuyerId(id);
    }
    public List<Courier> getTeacherCourierByBuyerId(long id) {
        return courierMapper.getTeacherCourierByBuyerId(id);
    }



    public List<Booklend> GetBookLendList() {
        return bookLendMapper.selectAllBookLend();
    }
    public List<Booklend> GetBookLendListByID(Long id) {
        return bookLendMapper.getBookLendById(id);
    }

    public List<Book> GetBookList() {
        return bookMapper.selectAllBook();
    }

    public List<CourseConnection> GetCourseConnectionList() {
        return courseConnectionMapper.selectAllCourseConnection();
    }

    public List<CourseConnection> getCourseConnectionByTeacherId(long  id) {
        return courseConnectionMapper.selectCourseConnectionByTeacherID(id);
    }

    public List<Course> getCourseList() {
        return courseMapper.selectAllCourse();
    }

    public List<Dormitory> getDormitoryList() {
        return dormitoryMapper.selectAllDormitory();
    }

    public List<Examination> getExaminationList() {
        return examinationMapper.selectAllExamination();
    }

    public List<GraduationConnection> getGraduationConnectionList() {
        return graduationConnectionMapper.selectAllGraduationConnection();
    }
    public GraduationConnection getGraduationConnectionListById(Long id) {
        return graduationConnectionMapper.selectAllGraduationConnectionById(id);
    }
    public List<GraduationConnection> getGraduationConnectionListByTeacherId(Long id) {
        return graduationConnectionMapper.selectAllGraduationConnectionByTeacherId(id);
    }

    public List<GraduationDesign> getGraduationDesignList() {
        return graduationDesignMapper.selectAllGraduationDesign();
    }

    public GraduationDesign getGraduationById(Long id) {
        return graduationDesignMapper.selectById(id);
    }

    public List<GraduationDesignTeacher> getGraduationDesignTeacherList() {
        return graduationDesignTeacherMapper.selectAllGraduationDesignTeacher();
    }

    public List<Score> getCourseScoreNotNullList() {
        return scoreMapper.selectAllCourseScoreNotNull();
    }
    public List<Score> getScoreList() {
        return scoreMapper.selectAllScore();

    }
    public List<Score> getScoreListById(long id) {
        return scoreMapper.selectAllScoreByStudentId(id);
    }

    public List<Student> getStudentList() {
        return studentMapper.getStudent();
    }

    public Student getStudentById(Long id) {
        return studentMapper.getStudentById(id);
    }


    public List<Teacher> getTeacherList() {
        return teacherMapper.selectAllTeacher();
    }

    public Teacher getTeacherById(long id) {
        return teacherMapper.selectTeacherById(id);
    }
    public List<GraduationDesignChect> getGraduationDesignChectList() {
        return graduationDesignChectMapper.getGraduationDesignChectList();
    }

    public List<HomeWork> getHomeWorkList() {
        return homeWorkMapper.getHomeWorkList();
    }

    public HomeWork getHomeWorkById(long id) {
        return homeWorkMapper.getHomeWorkById(id);
    }
    public List<HomeWorkFinish> getHomeWorkFinishList() {
        return homeWorkFinishmapper.getHomeWorkFinishList();
    }
    public List<HomeWorkFinish> getHomeWorkFinishListById(long id) {
        return homeWorkFinishmapper.getHomeWorkFinishListById(id);
    }

    public List<Meeting> getMeetingList() {
        return meetingMapper.selectAllMeeting();
    }
    public Meeting getMeetingById(long id) {
        return meetingMapper.selectAllMeetingById(id);
    }
    public List<MeetingConnection> getMeetingConnectionList() {
        return meetingConnectionMapper.selectAllMeetingConnection();
    }

    public List<MeetingConnection> getMeetingConnectionByTeacherId(long id) {
        return meetingConnectionMapper.selectMeetingConnectionByTeacherId(id);
    }

    public List<ElectricityCost> getElectricityCostList() {
        return electricityCostMapper.selectAllElectricityCost();
    }
    public ElectricityCost getElectricityCostByDormitoryId(long id) {
        return electricityCostMapper.selectElectricityCostByDormitoryId(id);
    }

}
