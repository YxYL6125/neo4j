package com.liyao.neo4j.repository.relationship;

import com.liyao.neo4j.eneity.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CreateTeacherRelation extends Neo4jRepository<Teacher, Long> {
    /**
     * 删除所有节点和关系
     */
    @Query("MATCH (n)\n" +
            "OPTIONAL MATCH (n)-[r]-()\n" +
            "DELETE n,r")
    void deleteAllNodeAndRelationship();
    /**
     * teacher-[课程老师]->course
     * @param courseConnection：连接表，连接course和teacher
     */
    @Query("match (t:teacher),(c:course) where t.id=:#{#courseConnection.teacherId} " +
            "and c.id=:#{#courseConnection.courseId} " +
            "merge (t)-[r:课程老师]->(c) ")
    void addTeachCourseRelation(CourseConnection courseConnection);

    /**
     * 布置作业
     */
    @Query("match(h:homework),(t:teacher)-[课程老师]->(c:course) where h.courseId=c.id \n" +
            "merge (t)-[r:布置作业{course:c.courseName}]->(h) ")
    void addHomeWorkRelation();

    /**
     *截至时间内未提交
     * @param homeWorkFinish
     */
    @Query("match (s:student),(h:homework) where s.id=:#{#homeWorkFinish.studentId} and " +
            "h.id=:#{#homeWorkFinish.homeworkId} " +
            "merge (s)-[r:未提交]->(h)")
    void addHomeWorkFinishNotApprovaRelation(HomeWorkFinish homeWorkFinish);

    /**
     * 已经提交
     * @param homeWorkFinish
     */
    @Query("match (s:student),(h:homework) where s.id=:#{#homeWorkFinish.studentId} and " +
            "h.id=:#{#homeWorkFinish.homeworkId} " +
            "merge (s)-[r:提交]->(h)")
    void addHomeWorkFinishApprovaRelation(HomeWorkFinish homeWorkFinish);

    /**
     * 规定时间内未提交，未完成
     * @param homeWorkFinish
     */
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
     * 创建快递关系
     */
    @Query("match (c:courier),(t:teacher) where c.identityCode ='1' and c.buyerId=t.id=:#{#teacher.id} \n" +
            "merge(t)-[r:快递]->(c)\n")
    void addCourierRelation(Teacher teacher);
    /**
     * 创建快递配送信息
     */
    @Query("match (t:teacher)-[r:`快递`]->(c:courier),(d:deliveryman) where c.id=:#{#courierConnection.courierId} " +
            "and d.id=:#{#courierConnection.deliverymanId}\n" +
            "merge (d)-[r1:配送]->(c)")
    void addDeliverymanRelation(CourierConnection courierConnection);

    /**
     * 创建快递已签收关系
     */
    @Query("match (t:teacher)-[r:`快递`]->(c:courier) where c.id=:#{#courier.id} " +
            "merge (t)-[r1:`已签收`{签收时间::#{#courier.takeTime}}]->(c)")
    void addCourierReceiveRelation(Courier courier);
    /**
     * 创建快递未签收关系
     */
    @Query("match (t:teacher)-[r:`快递`]->(c:courier) where c.id=$id " +
            "merge (t)-[r1:`未签收`]->(c)")
    void addCourierNotReceiveRelation(long id);

}
