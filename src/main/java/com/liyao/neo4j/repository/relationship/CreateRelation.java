package com.liyao.neo4j.repository.relationship;

import com.liyao.neo4j.eneity.*;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface CreateRelation extends Neo4jRepository<Student, Long> {
    /**
     * 清楚所有数据
     *
     */
    @Query("MATCH (n)\n" +
            "OPTIONAL MATCH (n)-[r]-()\n" +
            "DELETE n,r")
    void deleteAllNodeAndRelationship();

    /**
     * student和course的关系：student-课程->course
     */
    @Query("match (s:student),(c:course) where s.id=:#{#score.studentId} and c.id=:#{#score.courseId}  " +
            "merge (s)-[r:课程]->(c)")
    void addHaveCourseRelation(Score score);


    /**
     * teacher-[课程老师]->course
     *
     * @param courseConnection：连接表，连接course和teacher
     */
    @Query("match (t:teacher),(c:course) where t.id=:#{#courseConnection.teacherId} " +
            "and c.id=:#{#courseConnection.courseId} " +
            "merge (t)-[r:课程老师]->(c) ")
    void addTeachCourseRelation(CourseConnection courseConnection);

    /**
     * student-[宿舍]->dormitory
     */
    @Query("match(d:dormitory),(s:student) where s.dormitoryId=d.id \n" +
            "merge(s)-[r:宿舍]->(d) return r")
    void addDormitoryRelation();

    /**
     * 通过score表来链接student和course，score的分数大于等于60视为及格
     * student-[及格]->course
     */
    @Query("match(s:score),(sd:student),(c:course) where s.studentId=sd.id and s.courseId=c.id and  s.courseScore>=60 \n" +
            "merge(sd)-[r:及格{分数:s.courseScore}]->(c) return r")
    void addScorePassRelation();

    /**
     * 通过score表来链接student和course，score的分数小于60视为不及格
     * * student-[不及格]->course
     */
    @Query("match(s:score),(sd:student),(c:course) where s.studentId=sd.id and s.courseId=c.id and  s.courseScore<60 \n" +
            "merge(sd)-[r:不及格{分数:s.courseScore}]->(c) return r")
    void addScoreNoPassRelation();

    /**
     * 课程的考试相关信息，用于在临近考试两周时展示
     * student-[考试信息]->examination
     */
    @Query("match (e:examination),(s:student),(c:course) where s.id=:#{#score.studentId} and c.id=e.courseId=:#{#score.courseId} \n \n" +
            "merge(s)-[r:考试信息{课程:c.courseName}]->(e) ")
    void addExaminationRelation(Score score);

    /**
     * student-[借阅]->book
     *
     * @param booklend：连接student和book
     */
    @Query("match (s:student),(b:book) where s.id=:#{#booklend.studentId} and " +
            " b.id=:#{#booklend.bookId} " +
            " merge(s)-[r:借阅{lendTime::#{#booklend.lendTime}}]->(b)")
    void addBookLendRelation(Booklend booklend);

    /**
     * student-[归还]->book
     */
    @Query("match (s:student),(b:book) where s.id=:#{#booklend.studentId} and " +
            " b.id=:#{#booklend.bookId} " +
            " merge(s)-[r:归还{remainTime::#{#booklend.remainTime}}]->(b)")
    void addBookReturnRelation(Booklend booklend);

    /**
     * 距离归还日期一星期时
     * student-[未归还]->book
     */
    @Query("match (s:student),(b:book) where s.id=:#{#booklend.studentId} and " +
            " b.id=:#{#booklend.bookId} " +
            " merge(s)-[r:未归还{归还日期::#{#booklend.remainTime}}]->(b)")
    void addBookNotReturnRelation(Booklend booklend);

    /**
     * 超过默认一个月的归还日期时提示
     * student-[逾期未归还]->book
     */
    @Query("match (s:student),(b:book) where s.id=:#{#booklend.studentId} and " +
            " b.id=:#{#booklend.bookId} " +
            " merge(s)-[r:逾期未归还]->(b)")
    void addBookOverdueNotReturnRelation(Booklend booklend);

    /**
     * 毕业设计相关
     *
     * @param graduationConnection：连接毕设，学生，毕设老师
     */
    @Query("match(s:student),(t:graduation_design_teacher)\n" +
            "where s.id=:#{#graduationConnection.studentId} " +
            "and t.id=:#{#graduationConnection.graduationDesignTeacherId}\n" +
            "merge(s)-[r:指导老师]->(t)\n")
    void addGraduaitonTeacherRelation(GraduationConnection graduationConnection);

    /**
     * 未提交毕业设计题目
     *
     * @param graduationConnection
     */
    @Query("match(g:graduationDesign),(s:student)\n" +
            "where s.id=:#{#graduationConnection.studentId} " +
            "and g.id=:#{#graduationConnection.graduationDesignId} " +
            "merge(s)-[r:未提交课题]->(g)\n")
    void addNotSubjectRelation(GraduationConnection graduationConnection);

    /**
     * 已提交毕业设计题目
     *
     * @param graduationConnection
     */
    @Query("match(g:graduationDesign),(s:student),(t:graduation_design_teacher)\n" +
            "where s.id=:#{#graduationConnection.studentId} " +
            "and g.id=:#{#graduationConnection.graduationDesignId}\n" +
            "and t.id=:#{#graduationConnection.graduationDesignTeacherId}\n" +
            "merge(r:design_subject{name:g.subject,subjectId:g.id})\n" +
            "merge(s)-[r1:已提交课题]->(r)\n" +
            "merge(t)-[r2:审批]->(r)")
    void addSubjectRelation(GraduationConnection graduationConnection);

    /**
     * 未提交开题报告
     *
     * @param graduationConnection
     */
    @Query("match(g:graduationDesign),(s:student),(t:graduation_design_teacher)\n" +
            "where s.id=:#{#graduationConnection.studentId} " +
            "and g.id=:#{#graduationConnection.graduationDesignId} " +
            "merge(s)-[r:未提交开题报告]->(g)\n")
    void addNotReportRelation(GraduationConnection graduationConnection);

    /**
     * 已提交开题报告
     *
     * @param graduationConnection
     */
    @Query("match(g:graduationDesign),(s:student),(t:graduation_design_teacher)\n" +
            "where s.id=:#{#graduationConnection.studentId} " +
            "and g.id=:#{#graduationConnection.graduationDesignId}\n" +
            "and t.id=:#{#graduationConnection.graduationDesignTeacherId}\n" +
            "merge(r:design_report{name:g.designReport,reportId:g.id})\n" +
            "merge(s)-[r1:已提交开题报告]->(r)\n" +
            "merge(t)-[r2:审批]->(r)")
    void addReportRelation(GraduationConnection graduationConnection);

    /**
     * 未提交毕业论文
     *
     * @param graduationConnection
     */
    @Query("match(g:graduationDesign),(s:student),(t:graduation_design_teacher)\n" +
            "where s.id=:#{#graduationConnection.studentId} " +
            "and g.id=:#{#graduationConnection.graduationDesignId} " +
            "merge(s)-[r:未提交毕业论文]->(g)\n")
    void addNotPaperRelation(GraduationConnection graduationConnection);

    /**
     * 已提交毕业论文
     *
     * @param graduationConnection
     */
    @Query("match(g:graduationDesign),(s:student),(t:graduation_design_teacher)\n" +
            "where s.id=:#{#graduationConnection.studentId} " +
            "and g.id=:#{#graduationConnection.graduationDesignId}\n" +
            "and t.id=:#{#graduationConnection.graduationDesignTeacherId}\n" +
            "merge(r:design_paper{name:g.designPaper,paperId:g.id})\n" +
            "merge(s)-[r1:已提交论文]->(r)\n" +
            "merge(t)-[r2:审批]->(r)")
    void addPaperRelation(GraduationConnection graduationConnection);

    /**
     * 审批通过提交的课题题目
     */
    @Query("match (t:graduation_design_teacher)-[审批]->(n:design_subject) \n" +
            "where n.subjectId=:#{#graduationDesignChect.subjectId}\n" +
            "merge (t)-[r:通过]->(n) return r")
    void addSubjectPassRelation(GraduationDesignChect graduationDesignChect);

    /**
     * 审批未通过提交的课题题目
     */
    @Query("match (t:graduation_design_teacher)-[审批]->(n:design_subject) \n" +
            "where n.subjectId=:#{#graduationDesignChect.subjectId}\n" +
            "merge (t)-[r:未通过]->(n) return r")
    void addSubjectNoPassRelation(GraduationDesignChect graduationDesignChect);

    /**
     * 审批通过提交的开题报告
     */
    @Query("match (t:graduation_design_teacher)-[审批]->(n:design_report) \n" +
            "where n.reportId=:#{#graduationDesignChect.reportId}\n" +
            "merge (t)-[r:通过]->(n) return r")
    void addReportPassRelation(GraduationDesignChect graduationDesignChect);

    /**
     * 审批未通过提交的开题报告
     */
    @Query("match (t:graduation_design_teacher)-[审批]->(n:design_report) \n" +
            "where n.reportId=:#{#graduationDesignChect.reportId}\n" +
            "merge (t)-[r:未通过]->(n) return r")
    void addReportNoPassRelation(GraduationDesignChect graduationDesignChect);

    /**
     * 审批通过提交的论文
     */
    @Query("match (t:graduation_design_teacher)-[审批]->(n:design_paper) \n" +
            "where n.paperId=:#{#graduationDesignChect.paperId}\n" +
            "merge (t)-[r:通过]->(n) return r")
    void addPaperPassRelation(GraduationDesignChect graduationDesignChect);

    /**
     * 审批未通过提交的论文
     */
    @Query("match (t:graduation_design_teacher)-[审批]->(n:design_paper) \n" +
            "where n.paperId=:#{#graduationDesignChect.paperId}\n" +
            "merge (t)-[r:未通过]->(n) return r")
    void addPaperNoPassRelation(GraduationDesignChect graduationDesignChect);

    /**
     * 课程老师布置作业
     */
    @Query("match(h:homework),(t:teacher)-[课程老师]->(c:course) where h.courseId=c.id \n" +
            "merge (t)-[r:布置作业{course:c.courseName}]->(h) ")
    void addHomeWorkRelation();

    /**
     * 学生未提交作业
     *
     * @param homeWorkFinish
     */
    @Query("match (s:student),(h:homework) where s.id=:#{#homeWorkFinish.studentId} and " +
            "h.id=:#{#homeWorkFinish.homeworkId} " +
            "merge (s)-[r:未提交]->(h)")
    void addHomeWorkFinishNotApprovaRelation(HomeWorkFinish homeWorkFinish);

    @Query("match (s:student),(h:homework) where s.id=:#{#homeWorkFinish.studentId} and " +
            "h.id=:#{#homeWorkFinish.homeworkId} " +
            "merge (s)-[r:提交]->(h)")
    void addHomeWorkFinishApprovaRelation(HomeWorkFinish homeWorkFinish);

    @Query("match (s:student),(h:homework) where s.id=:#{#homeWorkFinish.studentId} and " +
            "h.id=:#{#homeWorkFinish.homeworkId} " +
            "merge (s)-[r:未完成]->(h)")
    void addHomeWorkNotFinishRelation(HomeWorkFinish homeWorkFinish);

    /**
     * 创建会议关系
     */
    @Query("match (m:meeting),(t:teacher) where m.id=:#{#meetingConnection.meetingId} " +
            "and t.id=:#{#meetingConnection.teacherId}\n" +
            "merge (t)-[r:会议{meetingTime:m.meetingTime}]->(m)")
    void addMeetingRelation(MeetingConnection meetingConnection);

    /**
     * 创建电费
     */
    @Query("match (d:dormitory),(e:electricityCost) where d.id=:#{#electricityCost.dormitoryId}\n" +
            "and e.id=:#{#electricityCost.id}\n" +
            "merge (d)-[r:未缴纳电费{费用::#{#electricityCost.electricityCost}}]->(e)")
    void addElectricityCostRelation(ElectricityCost electricityCost);

    /**
     * 创建快递关系
     */
    @Query("match (c:courier),(s:student) where c.identityCode='0' and c.buyerId=s.id \n" +
            "merge(s)-[r:快递]->(c)\n")
    void addCourierStudentRelation();

    @Query("match(c:courier),(t:teacher) where c.identityCode='1' and c.buyerId=t.id\n" +
            "merge(t)-[r:快递]->(c)")
    void addCourirerTeacherRelation();

    @Query("match (c:courier),(d:deliveryman) where c.id=:#{#courierConnection.courierId} " +
            "and d.id=:#{#courierConnection.deliverymanId}\n" +
            "merge (d)-[r:配送]->(c)")
    void addDeliverymanRelation(CourierConnection courierConnection);
}
