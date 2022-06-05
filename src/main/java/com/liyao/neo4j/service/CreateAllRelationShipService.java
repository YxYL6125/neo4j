package com.liyao.neo4j.service;

import com.liyao.neo4j.eneity.*;
import com.liyao.neo4j.mapper.CourseConnectionMapper;
import com.liyao.neo4j.repository.relationship.CreateRelation;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CreateAllRelationShipService {
    @Autowired
    CreateRelation createRelation;
    @Autowired
    GetNodeService getNodeService;
    //清空库里面的所有节点和关系
    public void deleteAllNodeAndRelationShip() {
        createRelation.deleteAllNodeAndRelationship();
    }
    //初始化所有关系
    public void InitRelationShip() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        //Score是联系course和student的表
        List<Score> scores = getNodeService.getScoreList();
        for (Score score : scores) {
            //学生课程关系
            createRelation.addHaveCourseRelation(score);
            //考试信息
            createRelation.addExaminationRelation(score);
        }

        //课程老师
        List<CourseConnection> courseConnections = getNodeService.GetCourseConnectionList();
        for (CourseConnection c : courseConnections) {
            createRelation.addTeachCourseRelation(c);
        }

        //建立会议关系
        List<MeetingConnection> meetingConnections = getNodeService.getMeetingConnectionList();
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
                createRelation.addMeetingRelation(meetingConnection);
            }
        }
        //建立寝室缴电费关系
        List<ElectricityCost> electricityCosts = getNodeService.getElectricityCostList();
        for (ElectricityCost electricityCost : electricityCosts) {
            //免费用电30度，超出的收费
            long useElectricity = electricityCost.getElectricityEnd() - electricityCost.getElectricityStart();
            if (useElectricity > 30) {
                //计算超出费用
                double fee = (useElectricity - 30) * electricityCost.getElectricityPrice();
                electricityCost.setElectricityCost(fee);
                createRelation.addElectricityCostRelation(electricityCost);
            }
        }

        //创建课程作业
        createRelation.addHomeWorkRelation();
        //创建课程作业提交情况
        List<HomeWorkFinish> homeWorkFinishes = getNodeService.getHomeWorkFinishList();
        for (HomeWorkFinish homeWorkFinish : homeWorkFinishes) {
            //通过连接表获取homework，来判断是否超过截至时间，
            HomeWork homeWork = getNodeService.getHomeWorkById(homeWorkFinish.getHomeworkId());
            //非0为提交
            if (homeWorkFinish.getHomeworkStatus() != 0) {
                createRelation.addHomeWorkFinishApprovaRelation(homeWorkFinish);
            }else if (homeWork.getEndTime().getTime()-date.getTime()>=0){
                createRelation.addHomeWorkFinishNotApprovaRelation(homeWorkFinish);
            }else {
                createRelation.addHomeWorkNotFinishRelation(homeWorkFinish);
            }
        }
        //创建宿舍关系
        createRelation.addDormitoryRelation();
        //创建有分数的学科
        createRelation.addScorePassRelation();
        createRelation.addScoreNoPassRelation();

        //借阅图书板块
        //默认借阅1个月
        List<Booklend> booklends = getNodeService.GetBookLendList();
        for (Booklend booklend : booklends) {
            //添加借阅关系
            createRelation.addBookLendRelation(booklend);
            //说明已经归还
            if (booklend.getRemainTime() != null) {
                createRelation.addBookReturnRelation(booklend);
            }//距离默认归还日期还有一星期时提示未归还  now-lend>=23  <=30
            else if ((date.getTime()-booklend.getLendTime().getTime())>=(23*1000 * 60 * 60 * 24)
            &&(date.getTime()-booklend.getLendTime().getTime())>=(30*1000 * 60 * 60 * 24)){
                calendar.setTime(booklend.getLendTime());
                Date remainDate = new Date();
                calendar.add(Calendar.MONTH,1);
                remainDate = calendar.getTime();
                booklend.setRemainTime(new Timestamp(remainDate.getTime()));
                createRelation.addBookNotReturnRelation(booklend);
            }
            //如果借阅日期距现在大于1个月,则创建逾期未归还
            else {
                createRelation.addBookOverdueNotReturnRelation(booklend);
            }
        }
        //毕业设计板块
        List<GraduationConnection> graduationConnections = getNodeService.getGraduationConnectionList();
        for (GraduationConnection graduationConnection : graduationConnections) {
            //添加毕业设计的指导老师关系
            createRelation.addGraduaitonTeacherRelation(graduationConnection);
            //通过获取连接表中该条数据的graduationDesign_id,来获得GraduationDesign
            GraduationDesign graduationDesign = getNodeService.getGraduationById(graduationConnection.getGraduationDesignId());
            //如果课题名称为空,则提示未提交毕业设计题目
            if (graduationDesign.getSubject().equals("1")) {
                createRelation.addNotSubjectRelation(graduationConnection);
            }
            else {
                //创建已提交课题
                createRelation.addSubjectRelation(graduationConnection);
                //如果开题报告为空,则提示未提交开题报告
                if (graduationDesign.getDesignReport().equals("1")) {
                    createRelation.addNotReportRelation(graduationConnection);
                }else {
                    //创建已经提交开题报告
                    createRelation.addReportRelation(graduationConnection);
                    //如果论文为空,则提示未提交论文
                    if (graduationDesign.getDesignPaper().equals("1")) {
                        createRelation.addNotPaperRelation(graduationConnection);
                    }else {
                        //创建已提交论文
                       createRelation.addPaperRelation(graduationConnection);
                    }
                }

            }
        }
        //毕业老师审批
        List<GraduationDesignChect> graduationDesignChects = getNodeService.getGraduationDesignChectList();
        for (GraduationDesignChect graduationDesignChect : graduationDesignChects) {
            //等于0则表示课题不通过
            if (graduationDesignChect.getSubjectResult() == 0) {
                //建立课题不通过关系
                createRelation.addSubjectNoPassRelation(graduationDesignChect);
            } else {
                //非0则表示通过,并建立通过关系
                createRelation.addSubjectPassRelation(graduationDesignChect);
                //等于0则表示课题不通过,等于1则表示通过其他则表示还没有审批结果直接跳过
                if (graduationDesignChect.getReportResult() == 0) {
                    //开题报告未通过
                    createRelation.addReportNoPassRelation(graduationDesignChect);
                } else if (graduationDesignChect.getReportResult() == 1) {
                    //开题报告通过
                    createRelation.addReportPassRelation(graduationDesignChect);
                    //论文未通过
                    if (graduationDesignChect.getPaperResult() == 0) {
                        createRelation.addPaperNoPassRelation(graduationDesignChect);
                    }//论文通过
                    else if (graduationDesignChect.getPaperResult() == 1) {
                        createRelation.addPaperPassRelation(graduationDesignChect);
                    }
                }
            }
        }
        //快递模块
        //创建学生和老师的快递信息
        createRelation.addCourierStudentRelation();
        createRelation.addCourirerTeacherRelation();
        //创建配送信息
        List<CourierConnection> courierConnections = getNodeService.getCourierConnection();
        for (CourierConnection courierConnection : courierConnections) {
            createRelation.addDeliverymanRelation(courierConnection);
        }


    }
}
