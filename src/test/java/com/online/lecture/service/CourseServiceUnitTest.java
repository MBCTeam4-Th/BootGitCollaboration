package com.online.lecture.service;

// JUnit assertions
import com.online.lecture.lecturePos.core.SellStatus;
import com.online.lecture.lecturePos.models.course.domain.Course;
import com.online.lecture.lecturePos.models.course.dto.postCourse.PostCourseReq;
import com.online.lecture.lecturePos.models.course.dto.postCourse.PostCourseRes;
import com.online.lecture.lecturePos.models.course.repository.CourseImgRepository;
import com.online.lecture.lecturePos.models.course.repository.CourseRepository;
import com.online.lecture.lecturePos.models.course.service.CourseImgService;
import com.online.lecture.lecturePos.models.course.service.CourseServiceImpl;
import com.online.lecture.lecturePos.models.course.service.FileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Mockito stubbing & verification
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class CourseServiceUnitTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseImgRepository courseImgRepository;

    @Mock
    private FileService fileService;

    @Mock
    private CourseImgService courseImgService;

    @InjectMocks
    private CourseServiceImpl courseService;  // 실제 구현체 사용

    @Test
    void testSaveCourse() {
        // given
        PostCourseReq req = PostCourseReq.builder()
                .courseName("Java Basics")
                .courseDescription("Java 기초 강좌")
                .courseFee(10000)
                .intemSellStatus(SellStatus.order)
                .maxEnrollment(30)
                .build();

        Course mockCourse = Course.builder()
                .courseId(1L)
                .courseName(req.getCourseName())
                .courseDescription(req.getCourseDescription())
                .courseFee(req.getCourseFee())
                .intemSellStatus(req.getIntemSellStatus())
                .maxEnrollment(req.getMaxEnrollment())
                .build();

        when(courseRepository.save(any(Course.class))).thenReturn(mockCourse);

        // when
        PostCourseRes res = courseService.saveCourse(req, Collections.emptyList());

        // then
        assertNotNull(res);
        assertEquals("Java Basics", res.getCourseDetail().getCourseName());
        verify(courseRepository, times(1)).save(any(Course.class));
    }


}
