package com.liyao.neo4j.repository.relationship;

import com.liyao.neo4j.eneity.GraduationConnection;
import com.liyao.neo4j.eneity.GraduationDesignChect;
import com.liyao.neo4j.eneity.GraduationDesignTeacher;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CreateGratualtionDesignTeacherRelation extends Neo4jRepository<GraduationDesignTeacher, Long> {
    /**
     * 毕业设计相关
     *
     * @param graduationConnection：连接毕设，学生，毕设老师
     */
        @Query("match(s:student),(t:graduation_design_teacher)\n" +
            "where s.id=:#{#graduationConnection.studentId} " +
            "and t.id=:#{#graduationConnection.graduationDesignTeacherId}\n" +
            "merge(s)-[r:指导老师]->(t)\n")
    void addGraduationTeacherRelation(GraduationConnection graduationConnection);

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
}
