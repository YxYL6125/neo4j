package com.liyao.neo4j.controller;

import com.liyao.neo4j.eneity.Teacher;
import com.liyao.neo4j.mapper.TeacherMapper;
import com.liyao.neo4j.service.CreateAllNodeService;
import com.liyao.neo4j.service.CreateTeacherRelationShipService;
import com.liyao.neo4j.service.GetNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    GetNodeService getNodeService;
    @Autowired
    CreateAllNodeService createAllNodeService;
    @Autowired
    CreateTeacherRelationShipService createTeacherRelationShipService;
    @Resource
    TeacherMapper teacherMapper;

    /**
     * 跳转老师页面
     * @param model
     * @return
     */
    @RequestMapping("/teacher")
    public String toTeacherPage(Model model) {
        List<Teacher> teachers = getNodeService.getTeacherList();
        model.addAttribute("teachers", teachers);
        return "TeacherDisplay";
    }

    /**
     * 修改老师信息
     * @param model
     * @param session
     * @param name
     * @param teacherTelephone
     * @return
     */
    @RequestMapping("/teacher/updateTeacher")
    public String updateTeacher(Model model, HttpSession session, @RequestParam("teacherName") String name,
                                @RequestParam("teacherTelephone") String teacherTelephone) {

        long teacherId = (long) session.getAttribute("teacherId");
        Teacher teacher = new Teacher();
        teacher.setId(teacherId);
        teacher.setTeacherName(name);
        teacher.setTeacherTelephone(teacherTelephone);
        teacherMapper.updateTeacher(teacher);
        List<Teacher> teachers = getNodeService.getTeacherList();
        model.addAttribute("teachers", teachers);
        return "TeacherDisplay";
    }

    /**
     *
     * @param id
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/teacher/update/{id}")
    public String toUpdateStudent(@PathVariable("id") long id, Model model, HttpSession session) {
        session.setAttribute("teacherId", id);
        Teacher teacher = getNodeService.getTeacherById(id);
        model.addAttribute("teacher", teacher);
        return "TeacherUpdate";
    }

    /**
     * 展示老师信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/teacher/display/{id}")
    public String DisplayTeacher(@PathVariable("id") long id, Model model) {
        Teacher teacher = getNodeService.getTeacherById(id);
        //清楚关系
        createTeacherRelationShipService.deleteAllNodeAndRelationShip();
        createAllNodeService.InitNode();
        createTeacherRelationShipService.InitTeacherRelationShip(teacher);
        model.addAttribute("msg", "教师视图展示");
        return "index";
    }

}
