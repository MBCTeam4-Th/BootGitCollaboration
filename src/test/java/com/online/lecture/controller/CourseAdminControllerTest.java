package com.online.lecture.controller;
import com.online.lecture.lecturePos.controller.course.CourseAdminController;
import com.online.lecture.lecturePos.models.course.dto.getCourse.GetCourseListReq;
import com.online.lecture.lecturePos.models.course.dto.getCourse.GetCourseListRes;
import com.online.lecture.lecturePos.models.course.dto.postCourse.PostCourseReq;
import com.online.lecture.lecturePos.models.course.dto.postCourse.PostCourseRes;
import com.online.lecture.lecturePos.models.course.service.CourseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseAdminController.class)
@AutoConfigureMockMvc(addFilters = false) // 보안 켜고 테스트 (원하면 false로 꺼도 됨)
class CourseAdminControllerTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean // ★ 실제 빈 대신 목으로 컨트롤러 생성자 주입
    CourseService courseService;

    @Test
    @DisplayName("파일 포함: 정상 등록시 목록으로 리다이렉트")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void register_ok_with_file() throws Exception {
        MockMultipartFile img = new MockMultipartFile(
                "courseImgFile", "img.jpg", MediaType.IMAGE_JPEG_VALUE, "dummy".getBytes());

        Mockito.when(courseService.saveCourse(any(PostCourseReq.class), any()))
                .thenReturn(PostCourseRes.builder().build());

        mvc.perform(
                        multipart("/admin/course/new")
                                .file(img) // ← 이게 없으면 컨트롤러에서 '첫번째 이미지 필수' 에러
                                .param("courseName", "MySQL 통합 테스트")
                                .param("courseDescription", "실제 DB 연동")
                                .param("courseFee", "15000")
                                .param("intemSellStatus", "order")
                                .param("maxEnrollment", "20")
                                .with(csrf())
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/course/list"));

        Mockito.verify(courseService).saveCourse(any(PostCourseReq.class), any());
    }

    @Test
    @DisplayName("파일 미첨부: 에러 메시지로 폼 다시 표시")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void register_fail_when_first_file_missing() throws Exception {
        mvc.perform(
                        multipart("/admin/course/new")
                                .param("courseName", "제목")
                                .with(csrf())
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(status().isOk())
                .andExpect(view().name("course/courseForm"))
                .andExpect(model().attributeExists("errorMessage"));
    }


    @Test
    @DisplayName("관리자 목록 페이지: 정상 렌더링")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void list_ok() throws Exception {
        // given: 페이지 객체 만들기
        var dto1 = new GetCourseListReq(1L, "스프링 입문", 10000, com.online.lecture.lecturePos.core.SellStatus.order, "/images/courses/a.jpg");
        var dto2 = new GetCourseListReq(2L, "JPA 고급", 20000, com.online.lecture.lecturePos.core.SellStatus.cancel, null);

        Page<GetCourseListReq> page = new PageImpl<>(
                List.of(dto1, dto2),
                PageRequest.of(0, 10),
                2
        );

        GetCourseListRes fake = GetCourseListRes.builder()
                .coursePage(page)
                .currentPage(0)
                .pageSize(10)
                .build();

        Mockito.when(courseService.getCourseList(any(GetCourseListReq.class), any()))
                .thenReturn(fake);

        // when + then
        mvc.perform(get("/admin/course/list")
                        .param("page", "0")
                        .param("size", "10")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("course/courseList"))
                .andExpect(model().attributeExists("response"))
                .andExpect(model().attribute("response", fake));
    }
}


