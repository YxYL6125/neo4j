package com.liyao.neo4j.service;

import com.liyao.neo4j.eneity.*;
import com.liyao.neo4j.repository.relationship.CreateStudentRelation;
import com.liyao.neo4j.repository.relationship.CreateTeacherRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * TeacherRelation
 */
@Service
public class CreateTeacherRelationShipService {
    @Autowired
    CreateTeacherRelation createTeacherRelation;
    @Autowired
    GetNodeService getNodeService;
    //清空库里面的所有节点和关系
    public void deleteAllNodeAndRelationShip() {
        createTeacherRelation.deleteAllNodeAndRelationship();
    }
    public void InitTeacherRelationShip(Teacher teacher) {
        Date date = new Date();
        //课程老师
        List<CourseConnection> courseConnections = getNodeService.getCourseConnectionByTeacherId(teacher.getId());
        for (CourseConnection c : courseConnections) {
            createTeacherRelation.addTeachCourseRelation(c);
        }
        //创建课程作业
        createTeacherRelation.addHomeWorkRelation();
        //创建课程作业提交情况
        List<HomeWorkFinish> homeWorkFinishes = getNodeService.getHomeWorkFinishList();
        for (HomeWorkFinish homeWorkFinish : homeWorkFinishes) {
            //通过连接表获取homework，来判断是否超过截至时间，
            HomeWork homeWork = getNodeService.getHomeWorkById(homeWorkFinish.getHomeworkId());
            //非0为提交
            if (homeWorkFinish.getHomeworkStatus() != 0) {
                createTeacherRelation.addHomeWorkFinishApprovaRelation(homeWorkFinish);
            }else if (homeWork.getEndTime().getTime()-date.getTime()>=0){
                createTeacherRelation.addHomeWorkFinishNotApprovaRelation(homeWorkFinish);
            }else {
                createTeacherRelation.addHomeWorkNotFinishRelation(homeWorkFinish);
            }
        }
        //创建会议关系
        List<MeetingConnection> meetingConnections = getNodeService.getMeetingConnectionByTeacherId(teacher.getId());
        for (MeetingConnection meetingConnection : meetingConnections) {
            //通过会议连接表中的id获取到meeting
            Meeting meeting = getNodeService.getMeetingById(meetingConnection.getMeetingId());
            //判断开会日期是否是当天
            Calendar meetingTime = Calendar.getInstance();
            Calendar nowTime= Calendar.getInstance();
            meetingTime.setTime(meeting.getMeetingTime());
            nowTime.setTime(date);
            //判断是否是同一天
            if (meetingTime.get(Calendar.YEAR)==nowTime.get(Calendar.YEAR)
                    &&meetingTime.get(Calendar.DAY_OF_YEAR)==nowTime.get(Calendar.DAY_OF_YEAR)){
                createTeacherRelation.addMeetingRelation(meetingConnection);
            }
        }
        //创建快递模块
        //创建快递关系
        createTeacherRelation.addCourierRelation(teacher);
        //创建配送关系
        List<CourierConnection> courierConnections = getNodeService.getCourierConnection();
        for (CourierConnection courierConnection : courierConnections) {
            createTeacherRelation.addDeliverymanRelation(courierConnection);
        }
        //创建快递接收未接收情况
        List<Courier> couriers = getNodeService.getTeacherCourierByBuyerId(teacher.getId());
        for (Courier courier : couriers) {
            if (courier.getTakeTime() == null) {
                createTeacherRelation.addCourierNotReceiveRelation(courier.getId());
            }else {
                createTeacherRelation.addCourierReceiveRelation(courier);
            }
        }


    }


}
