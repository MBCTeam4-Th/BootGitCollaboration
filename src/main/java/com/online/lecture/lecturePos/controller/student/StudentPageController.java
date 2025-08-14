package com.online.lecture.lecturePos.controller.student;

import com.online.lecture.lecturePos.models.student.dto.StudentDTO;
import com.online.lecture.lecturePos.models.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentPageController {
    private final StudentService studentService;

    // 회원가입 폼
    @GetMapping("/signup")
    public String signUpForm(Model model) {
        model.addAttribute("student", new StudentDTO());
        return "student/signUp";
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signUp(@ModelAttribute StudentDTO studentDTO) {
        studentService.signUp(studentDTO);
        return "redirect:/student/list";
    }

    // 학생 목록
    @GetMapping("/list")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "student/list";
    }

    // 수정 폼
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.findById(id));
        return "student/edit";
    }

    // 수정 처리
    @PostMapping("/edit")
    public String update(@ModelAttribute StudentDTO studentDTO) {
        studentService.update(studentDTO);
        return "redirect:/student/list";
    }

    // 삭제
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentService.delete(id);
        return "redirect:/student/list";
    }
}