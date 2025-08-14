package com.online.lecture.service;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import com.online.lecture.lecturePos.core.SellStatus;
import com.online.lecture.lecturePos.models.course.repository.CourseRepository;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "admin", roles = {"ADMIN"})
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CourseServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void testCourseCreateIntegration() throws Exception {
        // 테스트 메서드 내에서
        MockMultipartFile imageFile = new MockMultipartFile(
                "courseImgFile",              // 컨트롤러 파라미터명과 동일해야 함
                "test-image.jpg",
                "image/jpeg",
                "dummy image content".getBytes()
        );

        mockMvc.perform(multipart("/admin/course/new")
                        .file(imageFile)  // 이미지 파일 포함
                        .param("courseName", "MySQL 통합 테스트")
                        .param("courseDescription", "실제 DB 연동")
                        .param("courseFee", "15000")
                        .param("intemSellStatus", "order")
                        .param("maxEnrollment", "20")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());
    }
}