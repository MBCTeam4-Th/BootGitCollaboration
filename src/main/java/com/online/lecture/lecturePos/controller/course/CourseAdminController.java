package com.online.lecture.lecturePos.controller.course;

import com.online.lecture.lecturePos.models.course.dto.postCourse.PostCourseReq;
import com.online.lecture.lecturePos.models.course.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/course")
public class CourseAdminController {
    private final CourseService courseService;
    // 1. Course 등록폼   // 1. Course 등록 폼
    //    @GetMapping("/new")
    //    public String createForm(Model model){
    //        model.addAttribute("courseForm", new PostCourseReq());
    //        return "course/courseForm"; // 타임리프 form view
    //    }

    // 2. Course 등록처리
    @PostMapping("/new")
    public  String createCourse(@Valid PostCourseReq courseReq, BindingResult bindingResult,
                                @RequestParam("courseImgFile")List<MultipartFile> imgFiles, Model model) {

        // 2-1 DTO 검증
        if (bindingResult.hasErrors()) {
            return "course/courseForm";
        }

        // 2-2. 첫 이미지 필수 체크
        if(imgFiles.get(0).isEmpty()){
            model.addAttribute("errorMessage", "첫번째 이미지는 필수입니다.");
            return "course/courseForm";
        }

        try {
            // 2-3 Service 호출 -> Course + 이미지등록
            courseService.saveCourse(courseReq, imgFiles);
        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage()+"강좌 등록 중 오류 발생");
            return "course/courseForm";
        }
        // 3. 등록 성공 -> 목록 페이지 이동
        return "redirect:/admin/course/list";

    }

/*    // 3. Course 목록 페이지
    @GetMapping("/list")
    public String listCourses(Model model){
        model.addAttribute("courses", courseService.getAllCourses());
        return "course/courseList";
    }*/




} //CourseAdminController종료
