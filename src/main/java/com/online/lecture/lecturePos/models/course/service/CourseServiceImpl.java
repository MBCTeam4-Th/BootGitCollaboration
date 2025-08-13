package com.online.lecture.lecturePos.models.course.service;

import com.online.lecture.lecturePos.models.course.domain.Course;
import com.online.lecture.lecturePos.models.course.domain.CourseImg;
import com.online.lecture.lecturePos.models.course.dto.postCourse.PostCourseReq;
import com.online.lecture.lecturePos.models.course.repository.CourseImgRepository;
import com.online.lecture.lecturePos.models.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {
    
        @Autowired
        private CourseRepository courseRepository;
        @Autowired
        private CourseImgRepository courseImgRepository;
        @Autowired
        private FileService fileService;

        // course service 시작
        public List<Course> listAllCourses() {
            return courseRepository.findAll();
        }

        public Optional<Course> getCourse(Long courseId) {
            return courseRepository.findById(courseId);
        }
        // course service 종료
        
        // course imgService (대표이미지 관련)
        public List<CourseImg> getCourseImages(Course course) {
            return courseImgRepository.findByCourse(course);
        }




    @Override
    public PostCourseReq saveCourse(PostCourseReq request, List<MultipartFile> imgFiles) {
        //1.엔티티 생성, 저장
            Course course = Course.builder()
                .courseName(request.getCourseName())
                .courseDescription(request.getCourseDescription())
                .courseFee(request.getCourseFee())
                .intemSellStatus(request.getIntemSellStatus())
                .maxEnrollment(request.getMaxEnrollment())
                .build();
        courseRepository.save(course);

        //2. 이미지저장
        for (int i=0; i<imgFiles.size();i++){
            CourseImg courseImg = new CourseImg();
            courseImg.setCourse(course);

            if(i == 0)
                courseImg.setMainImgYn(true);
            else
                courseImg.setMainImgYn(false);

        }
            return null;
    }
}// course imgService 종료
