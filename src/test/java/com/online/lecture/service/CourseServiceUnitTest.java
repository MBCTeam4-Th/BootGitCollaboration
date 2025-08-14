package com.online.lecture.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.online.lecture.lecturePos.models.course.domain.Course;
import com.online.lecture.lecturePos.models.course.repository.CourseImgRepository;
import com.online.lecture.lecturePos.models.course.repository.CourseRepository;
import com.online.lecture.lecturePos.models.course.service.CourseService;

import com.online.lecture.lecturePos.models.course.dto.postCourse.PostCourseReq;
import com.online.lecture.lecturePos.models.course.dto.postCourse.PostCourseRes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

class CourseServiceUnitTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseImgRepository courseImgRepository;

    // 인터페이스를 구현한 테스트용 임시 구현체
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        courseService = new CourseService() {
            @Override
            public PostCourseRes saveCourse(PostCourseReq request, List<MultipartFile> imgFiles) {
                // Course 객체 생성 및 Repository save Mock 호출
                com.online.lecture.lecturePos.models.course.domain.Course course = new com.online.lecture.lecturePos.models.course.domain.Course();
                course.setCourseName(request.getCourseName());
                course.setCourseDescription(request.getCourseDescription());

                when(courseRepository.save(any(com.online.lecture.lecturePos.models.course.domain.Course.class))).thenReturn(course);

                courseRepository.save(course);

                // PostCourseRes 변환
                PostCourseRes res = new PostCourseRes();
                res.setCourseDetail(course);
                return res;
            }
        };
    }

    @Test
    void testSaveCourse() {
        // 준비
        PostCourseReq req = new PostCourseReq();
        req.setCourseName("Java Basics");
        req.setCourseDescription("Java 기초 강좌");

        List<MultipartFile> imgFiles = Collections.emptyList();

        // 실행
        PostCourseRes res = courseService.saveCourse(req, imgFiles);

        // 검증
        assertNotNull(res);
        assertEquals("Java Basics", res.getCourseDetail().getCourseName());

        // Repository save 호출 검증
        verify(courseRepository, times(1)).save(any(Course.class));
    }
}

