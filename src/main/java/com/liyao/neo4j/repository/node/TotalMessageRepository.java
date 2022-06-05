package com.liyao.neo4j.repository.node;

import com.liyao.neo4j.eneity.TotalMessage;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface TotalMessageRepository extends Neo4jRepository<TotalMessage,Long> {
    @Query("merge " +
            //student
            "(s:student {姓名: :#{#totalMessage.studentName}," +
            "年龄::#{#totalMessage.age}," +
            "专业::#{#totalMessage.speciality}" +
            ",一卡通卡号::#{#totalMessage.cardNumber}" +
            ",性别::#{#totalMessage.sex}," +
            "学院::#{#totalMessage.college}})" +","+
            //dormitory
            "(d:dormitory{楼栋名称::#{#totalMessage.bulidName}," +
            "层数::#{#totalMessage.floor}," +
            "寝室号::#{#totalMessage.room}})" +","+
            //course
            "(c:course{课程名称::#{#totalMessage.courseName}," +
            "上课教室::#{#totalMessage.courseClassroom}})"+ ","+
            //score
            "(score:score{分数::#{#totalMessage.courseScore}})" +","+
            //examination
            "(e:examination{考试教室::#{#totalMessage.examinationClassroom}," +
            "开始时间::#{#totalMessage.startTime}," +
            "结束时间::#{#totalMessage.endTime}})"+","+
//            teacher
            "(t:teacher{老师名称::#{#totalMessage.teacherName}," +
            "老师电话::#{#totalMessage.teacherTelephone}})"  +","+
            //graduationDesign
            "(g:graduationDesign{指导老师::#{#totalMessage.teacherName}," +
            "题目::#{#totalMessage.subject}})"
            )
    void createNodeAndRelationship(TotalMessage totalMessage);


}
