package com.online.lecture.lecturePos.controller.course;

import com.online.lecture.lecturePos.models.course.dto.getCourse.GetCourseListReq;
import com.online.lecture.lecturePos.models.course.dto.getCourse.GetCourseListRes;
import com.online.lecture.lecturePos.models.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student/courses")
public class CoursePageController { // 학생용

    private final CourseService courseService;

    @GetMapping("/list")
public String GetListForStudent(@ModelAttribute GetCourseListReq courseReq,
                                Pageable pageable, Model model) {
        GetCourseListRes response = courseService.getCourseList(courseReq,pageable);
        model.addAttribute("response", response);
        return "course/courseListForStudent";
    }

    @GetMapping("/{courseId}")
    public String GetDetailForStudent(@PathVariable Long courseId, Model model) {
        var response = courseService.getCourseDetail(courseId);
        model.addAttribute("response", response);
        return "course/courseDetailForStudent";
    }

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