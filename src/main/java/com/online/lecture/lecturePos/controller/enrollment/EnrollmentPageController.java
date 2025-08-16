
package com.online.lecture.lecturePos.controller.enrollment;


import com.online.lecture.lecturePos.models.student.domain.Student;
import com.online.lecture.lecturePos.models.enrollment.service.EnrollmentService;
import com.online.lecture.lecturePos.models.student.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/enrollment")
public class EnrollmentPageController {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private StudentService studentService;


    @PostMapping("/enroll/{courseId}")
    public String enroll(@PathVariable("courseId") Long courseId, HttpSession session) {

        String email = (String) session.getAttribute("userEmail");

        Student student = studentService.findByEmail(email);

        enrollmentService.enroll(student, courseId);

        return "redirect:/enrollment/my";
    }


    @GetMapping("/my")
    public String myEnrollments(HttpSession session, Model model) {

        String email = (String) session.getAttribute("userEmail");

        Student student = studentService.findByEmail(email);

        model.addAttribute("enrollments", enrollmentService.getMyEnrollments(student));

        return "enrollment/myEnrollments";
    }


}





