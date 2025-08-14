package com.online.lecture.lecturePos.controller.course;

import com.online.lecture.lecturePos.models.course.domain.Course;
import com.online.lecture.lecturePos.models.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courses")
public class CoursePageController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String listCourses(Model model) {

        model.addAttribute("courses", courseService.listAllCourses());

        return "course/list";
    }

    @GetMapping("/courses/{courseId}")
    public String courseDetail(@PathVariable("courseId") Long courseId, Model model) {

        Course course = courseService.getCourse(courseId).orElseThrow();

        model.addAttribute("course", course);
        model.addAttribute("images", courseService.getCourseImages(course));

        return "course/detail";
    }

}
