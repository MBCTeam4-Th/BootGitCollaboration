package com.online.lecture.lecturePos.controller.course;

import com.online.lecture.lecturePos.models.course.domain.Course;
import com.online.lecture.lecturePos.models.course.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courses")
public class CoursePageController {
/*    @Autowired
    private CourseServiceImpl courseServiceImpl;

    @GetMapping
    public String listCourses(Model model) {

        model.addAttribute("courses", courseServiceImpl.listAllCourses());

        return "course/list"; //목록템플릿 학생용
    }

    @GetMapping("/{courseId}")
    public String courseDetail(@PathVariable("courseId") Long courseId, Model model) {

        Course course = courseServiceImpl.getCourse(courseId).orElseThrow();

        model.addAttribute("course", course);
        model.addAttribute("images", courseServiceImpl.getCourseImages(course));

        return "course/detail"; // 상세템플릿 학생용

    }*/

}