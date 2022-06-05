package com.liyao.neo4j.controller;

import com.liyao.neo4j.common.ResponseResult;
import com.liyao.neo4j.eneity.Student;
import com.liyao.neo4j.mapper.StudentMapper;
import com.liyao.neo4j.service.CreateAllNodeService;
import com.liyao.neo4j.service.CreateStudentRelationShipService;
import com.liyao.neo4j.service.GetNodeService;
import com.liyao.neo4j.service.StudentService;
import com.liyao.neo4j.utils.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.awt.event.MouseAdapter;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    GetNodeService getNodeService;
    @Autowired
    CreateAllNodeService createAllNodeService;
    @Autowired
    CreateStudentRelationShipService createStudentRelationShipService;
    @Resource
    StudentMapper studentMapper;
    @Autowired
    StudentService studentService;

    /**
     * 跳转学生界面
     *
     * @param model
     * @return
     */
    @RequestMapping("/student")
    public String toStudentPage(Model model) {
        List<Student> students = getNodeService.getStudentList();
        model.addAttribute("students", students);
        return "StudentDisplay";
    }

    /**
     * 修改学生信息
     *
     * @param model
     * @param session
     * @param name
     * @param cardNumber
     * @param sex
     * @param speciality
     * @param college
     * @param dormitoryId
     * @param age
     * @return
     */
    @RequestMapping("/student/updateStudent")
    public String updateStudent(Model model, HttpSession session,
                                @RequestParam("studentName") String name, @RequestParam("cardNumber") long cardNumber,
                                @RequestParam("sex") String sex, @RequestParam("speciality") String speciality,
                                @RequestParam("college") String college, @RequestParam("dormitoryId") long dormitoryId,
                                @RequestParam("age") long age) {
        long studentId = (long) session.getAttribute("studentId");
        Student student = new Student();
        student.setId(studentId);
        student.setStudentName(name);
        student.setCardNumber(cardNumber);
        student.setSex(sex);
        student.setSpeciality(speciality);
        student.setCollege(college);
        student.setDormitoryId(dormitoryId);
        student.setAge(age);
        studentMapper.updateStudent(student);
        List<Student> students = getNodeService.getStudentList();
        model.addAttribute("students", students);
        return "StudentDisplay";
    }

    /**
     * 跳转修改学生界面
     *
     * @param id
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/student/update/{id}")
    public String toUpdateStudent(@PathVariable("id") long id, Model model, HttpSession session) {
        session.setAttribute("studentId", id);
        Student student = getNodeService.getStudentById(id);
        model.addAttribute("student", student);
        return "StudentUpdate";
    }

    /**
     * 展示学生信息
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/student/display/{id}")
    public String DisplayStudent(@PathVariable("id") long id, Model model) {
        Student student = getNodeService.getStudentById(id);
        //清除关系
        createStudentRelationShipService.deleteAllNodeAndRelationShip();
        createAllNodeService.InitNode();
        createStudentRelationShipService.InitStudentRelationShip(student);
        model.addAttribute("msg", "学生视图展示");
        return "index";
    }


    /**
     * 添加学生信息
     *
     * @param model
     * @param studentId
     * @param name
     * @param cardNumber
     * @param sex
     * @param speciality
     * @param college
     * @param dormitoryId
     * @param age
     * @return
     */
    @PostMapping("/student/add")
    public String addStudent(Model model,
                             @RequestParam("id") long studentId, @RequestParam("studentName") String name, @RequestParam("cardNumber") long cardNumber,
                             @RequestParam("sex") String sex, @RequestParam("speciality") String speciality,
                             @RequestParam("college") String college, @RequestParam("dormitoryId") long dormitoryId,
                             @RequestParam("age") long age) {


        Student student = new Student();
        student.setId(studentId);
        student.setStudentName(name);
        student.setCardNumber(cardNumber);
        student.setSex(sex);
        student.setSpeciality(speciality);
        student.setCollege(college);
        student.setDormitoryId(dormitoryId);
        student.setAge(age);
        studentMapper.addStudent(student);

        List<Student> students = getNodeService.getStudentList();
        model.addAttribute("students", students);

        return "StudentDisplay";

    }


    /**
     * 删除学生信息
     * @param model
     * @param id 学生id
     * @return
     */
    @PostMapping("/student/del")
    public String delStudentById(Model model, @RequestParam("id") long id) {
        //检验id是否为空
        if (SystemUtil.isNullOrEmpty(String.valueOf(id))) {
            ResponseResult responseResult = new ResponseResult();
            model.addAttribute("result", responseResult);
        }

        boolean bl = studentService.delStudent(id);
        if (bl) {//删除成功
            List<Student> students = getNodeService.getStudentList();
            model.addAttribute("students", students);
        } else {//删除失败
            model.addAttribute("msg","id为空");
        }

        return "StudentDisplay";
    }
}
