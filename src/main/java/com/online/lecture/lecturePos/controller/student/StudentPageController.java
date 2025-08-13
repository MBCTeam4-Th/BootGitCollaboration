package com.online.lecture.lecturePos.controller.student;

import com.online.lecture.lecturePos.models.student.domain.Student;
import com.online.lecture.lecturePos.models.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentPageController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/signup")
    public String signUpForm(Model model) {

        model.addAttribute("student", new Student());

        return "student/signUp";
    }

    @PostMapping("/signup")

    public String signUp(@ModelAttribute Student student) {

        studentService.signUp(student);

        return "redirect:/";
    }

}





