package com.online.lecture.lecturePos.controller.course;

import com.online.lecture.lecturePos.models.course.dto.getCouresDetail.GetCourseDetailReq;
import com.online.lecture.lecturePos.models.course.dto.getCourse.GetCourseListReq;
import com.online.lecture.lecturePos.models.course.dto.getCourse.GetCourseListRes;
import com.online.lecture.lecturePos.models.course.dto.modifyCourse.ModifyCourseReq;
import com.online.lecture.lecturePos.models.course.dto.modifyCourse.ModifyCourseRes;
import com.online.lecture.lecturePos.models.course.dto.postCourse.PostCourseReq;
import com.online.lecture.lecturePos.models.course.service.CourseImgService;
import com.online.lecture.lecturePos.models.course.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/course")
public class CourseAdminController {


    private final CourseService courseService;
    private final CourseImgService courseImgService;

    //1-1. Course 등록 폼 진입
     @GetMapping("/new")
  public String createForm(Model model){
        model.addAttribute("courseForm", new PostCourseReq());
      return "course/courseForm"; // 타임리프 form view
       }

    //1-2. Course 등록처리
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
        //빈리스트 / 첫파일 비어있음 모두 방어
        boolean firstMissing = (imgFiles == null || imgFiles.isEmpty() || imgFiles.get(0).isEmpty());
        if(firstMissing){
            model.addAttribute("errorMessage", "첫번째 이미지는 필수입니다.");
            return "course/courseForm";
        }

//        // 2-2. 첫 이미지 필수 체크
//        if(imgFiles != null && !imgFiles.isEmpty()){
//            model.addAttribute("errorMessage", "첫번째 이미지는 필수입니다.");
//            return "course/courseForm";
//        }

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

   //2-1. Course 목록 페이지
    @GetMapping("/list")
    public String getCourseList(@ModelAttribute GetCourseListReq courseReq,
                                Pageable pageable, Model model){
        // 관리자 목록 : GET /admin/course/list 테이블로 강의 리스트 표시 (이름/ 수강료/상태/상세보기 링크) + 페이지네이션 (간단)

        //Page<GetCourseListRes> courseList = courseService.getCourseList(courseReq, pageable);
        GetCourseListRes response = courseService.getCourseList(courseReq,pageable);
        model.addAttribute("response", response);
        return "course/courseList";
    }

//2-2. Course 목록 상세페이지
    @GetMapping("/{courseId}")
    public String getCourseDetail(@PathVariable Long courseId, Model model){
        // 관리자 상세 : GET /admin/course/{courseId}

        var response = courseService.getCourseDetail(courseId);
        model.addAttribute("response", response);
        return "course/courseDetail";
    }

   //3-1 Course 수정 폼 진입
   @GetMapping("/{courseId}/modify")
   public String ModifyForm(@PathVariable Long courseId, Model model) {
       var response = courseService.getCourseDetail(courseId);
       model.addAttribute("response", response);
       return "course/courseModify";
   }

   //3-2 Course 수정
    @PostMapping("/{courseId}/modify")
    public String ModifyCourse(@PathVariable Long courseId,
                               @Valid ModifyCourseReq courseReq,
                               BindingResult bindingResult,
                               @RequestParam(value = "courseImgFile", required = false) List<MultipartFile> newImgs,
                               Model model){
         if (bindingResult.hasErrors()) {
             //폼 재표시 : 기존 입력값을 그대로 response.modifiedCourse에 실어주려면
             GetCourseDetailReq fallback = GetCourseDetailReq.builder()
                     .courseId(courseReq.getCourseId())
                     .courseName(courseReq.getCourseName())
                     .courseDescription(courseReq.getCourseDescription())
                     .courseFee(courseReq.getCourseFee())
                     .intemSellStatus(courseReq.getIntemSellStatus())
                     .maxEnrollment(courseReq.getMaxEnrollment())
                     .mainImageUrl(null) // 검증실패시 대표이미지 URL은 생략
                     .build();

             model.addAttribute("response", ModifyCourseRes.builder().courseDetail(fallback).build());
             return "course/courseModify";
         }
         var response = courseService.modifyCourse(courseReq, courseId, newImgs); // 새메서드추가
        // 수정 후 폼 그대로 다시 보여주기
        //model.addAttribute("response", response);
        //return "course/courseModify";.

        return "redirect:/admin/course/"+courseId;
    }

//4-1 Course 삭제
    @PostMapping("/{courseId}/delete")
    public String deleteCourse(@PathVariable Long courseId, Model model){
         courseService.deleteCourse(courseId);
         return "redirect:/admin/course/list";
    }

    // 4-2이미지삭제
    @PostMapping("/{courseId}/img/delete")
    public String deleteCourseImg(@PathVariable Long courseId,
                                  @RequestParam("imgUrl") String imgUrl){
         courseImgService.deleteCourseImg(courseId, imgUrl);
         return "redirect:/admin/course/"+courseId+"/modify";
    }




} //CourseAdminController종료
