package com.online.lecture.service;


import com.online.lecture.lecturePos.core.SellStatus;
import com.online.lecture.lecturePos.models.course.dto.postCourse.PostCourseReq;
import com.online.lecture.lecturePos.models.course.repository.CourseImgRepository;
import com.online.lecture.lecturePos.models.course.repository.CourseRepository;
import com.online.lecture.lecturePos.models.course.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class CourseServiceTest {

    //@Autowired
    @InjectMocks
    private CourseService courseService;

    //@Autowired
    //@MockBean
    @Mock
    private CourseRepository courseRepository;

    //@Autowired
    @Mock
    private CourseImgRepository courseImgRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCourse() {
        // 여기에 테스트 로직 작성
        // 1. PostCourseReq 객체 생성
        PostCourseReq req = new PostCourseReq();
        req.setCourseName("테스트 강좌");
        req.setCourseDescription("설명");
        req.setCourseFee(10000);
        req.setIntemSellStatus(SellStatus.order);
        req.setMaxEnrollment(20);

        // 2. 이미지 파일 리스트 (테스트용 빈 MultipartFile)
       // List<MultipartFile> imgFiles = new ArrayList<>();
        // 필요시 MockMultipartFile 사용 가능
        // imgFiles.add(new MockMultipartFile("file", "test.jpg", "image/jpeg", "dummy content".getBytes()));
/*
        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                "test.jpg",
                "image/jpeg",
                "dummy content".getBytes()
        );
        imgFiles.add(mockFile);*/
        List<MultipartFile> imgFiles = new ArrayList<>();


        // 3. 서비스 호출
        courseService.saveCourse(req, imgFiles);

        // 4. DB 확인 (assert, System.out.println 등)
       /* assertEquals(1, courseRepository.count());
        assertEquals(1, courseImgRepository.count());
        assertEquals("테스트 강좌", courseRepository.findAll().get(0).getCourseName());
        System.out.println("저장 완료");*/
        // Repository 메서드 호출 여부 확인
        verify(courseRepository, times(1)).save(any());
        System.out.println("CourseService 단위 테스트 완료");
    }
}
