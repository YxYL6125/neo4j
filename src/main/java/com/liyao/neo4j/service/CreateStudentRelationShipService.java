package com.liyao.neo4j.service;

import com.liyao.neo4j.eneity.*;
import com.liyao.neo4j.repository.relationship.CreateRelation;
import com.liyao.neo4j.repository.relationship.CreateStudentRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CreateStudentRelationShipService {
    @Autowired
    CreateStudentRelation createStudentRelation;
    @Autowired
    GetNodeService getNodeService;
    //清空库里面的所有节点和关系
    public void deleteAllNodeAndRelationShip() {
        createStudentRelation.deleteAllNodeAndRelationship();
    }
    //Student
    public void InitStudentRelationShip(Student student) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        //创建课程关系
        List<Score> scores = getNodeService.getScoreListById(student.getId());
        for (Score score : scores) {
            createStudentRelation.addHaveCourseRelation(score);
            //考试信息
            createStudentRelation.addExaminationRelation(score);
        }
        //创建宿舍关系
        createStudentRelation.addDormitoryRelation(student);
        //如果欠费则创建寝室电费关系
        ElectricityCost electricityCost = getNodeService.getElectricityCostByDormitoryId(student.getDormitoryId());
        //免费用电30度，超出的收费
        long useElectricity = electricityCost.getElectricityEnd() - electricityCost.getElectricityStart();
        if (useElectricity > 30) {
            //计算超出费用
            double fee = (useElectricity - 30) * electricityCost.getElectricityPrice();
            electricityCost.setElectricityCost(fee);
            createStudentRelation.addElectricityCostRelation(electricityCost);
        }
        //创建有分数的学科
        createStudentRelation.addScorePassRelation(student);
        createStudentRelation.addScoreNoPassRelation(student);

        //借阅图书板块
        //默认借阅1个月
        List<Booklend> bookends = getNodeService.GetBookLendListByID(student.getId());
        for (Booklend booklend : bookends) {
            //添加借阅关系
            createStudentRelation.addBookLendRelation(booklend);
            //说明已经归还
            if (booklend.getRemainTime() != null) {
                createStudentRelation.addBookReturnRelation(booklend);
            }//距离默认归还日期还有一星期时提示未归还  now-lend>=23  <=30
            else if ((date.getTime() - booklend.getLendTime().getTime()) >= (23 * 1000 * 60 * 60 * 24)
                    && (date.getTime() - booklend.getLendTime().getTime()) >= (30 * 1000 * 60 * 60 * 24)) {
                calendar.setTime(booklend.getLendTime());
                Date remainDate = new Date();
                calendar.add(Calendar.MONTH, 1);
                remainDate = calendar.getTime();
                booklend.setRemainTime(new Timestamp(remainDate.getTime()));
                createStudentRelation.addBookNotReturnRelation(booklend);
            }
            //如果借阅日期距现在大于1个月,则创建逾期未归还
            else {
                createStudentRelation.addBookOverdueNotReturnRelation(booklend);
            }
        }



        //作业板块
        //创建课程作业提交情况
        List<HomeWorkFinish> homeWorkFinishes = getNodeService.getHomeWorkFinishListById(student.getId());
        for (HomeWorkFinish homeWorkFinish : homeWorkFinishes) {
            //通过连接表获取homework，来判断是否超过截至时间，
            HomeWork homeWork = getNodeService.getHomeWorkById(homeWorkFinish.getHomeworkId());
            //非0为提交
            if (homeWorkFinish.getHomeworkStatus() != 0) {
                createStudentRelation.addHomeWorkFinishApprovalRelation(homeWorkFinish);
            }else if (homeWork.getEndTime().getTime()-date.getTime()>=0){
                createStudentRelation.addHomeWorkFinishNotApprovaRelation(homeWorkFinish);
            }else {
                createStudentRelation.addHomeWorkNotFinishRelation(homeWorkFinish);
            }
        }

        //毕业设计板块
        GraduationConnection graduationConnection = getNodeService.getGraduationConnectionListById(student.getId());
        //添加毕业设计的指导老师关系
        createStudentRelation.addGraduaitonTeacherRelation(graduationConnection);
        //通过获取连接表中该条数据的graduationDesign_id,来获得GraduationDesign
        GraduationDesign graduationDesign = getNodeService.getGraduationById(graduationConnection.getGraduationDesignId());
        //如果课题名称为空,则提示未提交毕业设计题目
        if (graduationDesign.getSubject().equals("1")) {
            createStudentRelation.addNotSubjectRelation(graduationConnection);
        } else {
            //创建已提交课题
            createStudentRelation.addSubjectRelation(graduationConnection);
            //如果开题报告为空,则提示未提交开题报告
            if (graduationDesign.getDesignReport().equals("1")) {
                createStudentRelation.addNotReportRelation(graduationConnection);
            } else {
                //创建已经提交开题报告
                createStudentRelation.addReportRelation(graduationConnection);
                //如果论文为空,则提示未提交论文
                if (graduationDesign.getDesignPaper().equals("1")) {
                    createStudentRelation.addNotPaperRelation(graduationConnection);
                } else {
                    //创建已提交论文
                    createStudentRelation.addPaperRelation(graduationConnection);
                }
            }

        }
        //快递板块
        //创建快递关系
        createStudentRelation.addCourierRelation(student);
        //创建配送关
        List<CourierConnection> courierConnections = getNodeService.getCourierConnection();
        for (CourierConnection courierConnection : courierConnections) {
            createStudentRelation.addDeliverymanRelation(courierConnection);
        }
        //创建快递接收未接收情况
        List<Courier> couriers = getNodeService.getStudentCourierByBuyerId(student.getId());
        for (Courier courier : couriers) {
            if (courier.getTakeTime() == null) {
                createStudentRelation.addCourierNotReceiveRelation(courier.getId());
            }else {
                createStudentRelation.addCourierReceiveRelation(courier);
            }
        }

    }
}
