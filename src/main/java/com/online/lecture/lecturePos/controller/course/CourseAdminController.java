package com.online.lecture.lecturePos.controller.course;

import com.online.lecture.lecturePos.models.course.dto.postCourse.PostCourseReq;
import com.online.lecture.lecturePos.models.course.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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

    //1. Course 등록폼   // 1. Course 등록 폼
     @GetMapping("/new")
  public String createForm(Model model){
        model.addAttribute("courseForm", new PostCourseReq());
      return "course/courseForm"; // 타임리프 form view
       }

    // 2. Course 등록처리
    @PostMapping("/new")
    public  String createCourse(@Valid PostCourseReq courseReq,
                                BindingResult bindingResult,
                                @RequestParam("courseImgFile")List<MultipartFile> imgFiles,
                                Model model) {

        // 2-1 DTO 검증
        if (bindingResult.hasErrors()) {
            return "course/courseForm"; //오류시 다시 폼 보여주기(기존 입력값 유지)
        }

        // 2-2. 첫 이미지 필수 체크
        if(imgFiles != null && !imgFiles.isEmpty()){
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
        return "redirect:/admin/course/list"; // course, images를 모델에 담아 detail.html로 이동


    }

   //3. Course 목록 페이지
    @GetMapping("/list")
    public String listCourses(Long courseId, Model model){
         //테스트하기위해서 비워둠
        model.addAttribute("courses", courseService.getCourseDetail(courseId));
        return "course/courseList";
    }
    // 관리자 목록 : GET /admin/course/list 테이블로 강의 리스트 표시 (이름/ 수강료/상태/상세보기 링크) + 페이지네이션 (간단)

    // 관리자 상세 : GET /admin/course/{courseId}  상세 화면으로 등록된 정보 확인(수정/삭제는 나중단계)
    //이미지 연동은 결합 포인트이기때문에 일단 제외. 나중에 대표 이미지 URL을 선택적으로 끼워넣을 수 있도록함.





} //CourseAdminController종료
