package com.liyao.neo4j.service;

import com.liyao.neo4j.eneity.GraduationConnection;
import com.liyao.neo4j.eneity.GraduationDesign;
import com.liyao.neo4j.eneity.GraduationDesignChect;
import com.liyao.neo4j.eneity.GraduationDesignTeacher;
import com.liyao.neo4j.repository.relationship.CreateGratualtionDesignTeacherRelation;
import com.liyao.neo4j.repository.relationship.CreateRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateGraduationDesignTeacherRelationShipService {
    @Autowired
    CreateRelation createRelation;
    @Autowired
    GetNodeService getNodeService;
    //清空库里面的所有节点和关系
    public void deleteAllNodeAndRelationShip() {
        createRelation.deleteAllNodeAndRelationship();
    }
    public void Init(GraduationDesignTeacher teacher) {
        //毕业设计板块
        List<GraduationConnection> graduationConnections = getNodeService.getGraduationConnectionListByTeacherId(teacher.getId());
        for (GraduationConnection graduationConnection : graduationConnections) {
            //添加毕业设计的指导老师关系
            createRelation.addGraduaitonTeacherRelation(graduationConnection);
            //通过获取连接表中该条数据的graduationDesign_id,来获得GraduationDesign
            GraduationDesign graduationDesign = getNodeService.getGraduationById(graduationConnection.getGraduationDesignId());
            //如果课题名称为空,则提示未提交毕业设计题目
            if (graduationDesign.getSubject().equals("1")) {
                createRelation.addNotSubjectRelation(graduationConnection);
            } else {
                //创建已提交课题
                createRelation.addSubjectRelation(graduationConnection);
                //如果开题报告为空,则提示未提交开题报告
                if (graduationDesign.getDesignReport().equals("1")) {
                    createRelation.addNotReportRelation(graduationConnection);
                } else {
                    //创建已经提交开题报告
                    createRelation.addReportRelation(graduationConnection);
                    //如果论文为空,则提示未提交论文
                    if (graduationDesign.getDesignPaper().equals("1")) {
                        createRelation.addNotPaperRelation(graduationConnection);
                    } else {
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
    }
}
