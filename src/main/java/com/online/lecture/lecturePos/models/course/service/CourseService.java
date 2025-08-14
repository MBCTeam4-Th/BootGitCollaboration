package com.online.lecture.lecturePos.models.course.service;

import com.online.lecture.lecturePos.models.course.domain.Course;
import com.online.lecture.lecturePos.models.course.domain.CourseImg;
import com.online.lecture.lecturePos.models.course.repository.CourseImgRepository;
import com.online.lecture.lecturePos.models.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CourseService {
    
        @Autowired
        private CourseRepository courseRepository;
        
        @Autowired
        private CourseImgRepository courseImgRepository;

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
        // course imgService 종료
    




}
