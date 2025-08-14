package com.online.lecture.lecturePos.models.course.repository;

import com.online.lecture.lecturePos.models.course.domain.Course;
import com.online.lecture.lecturePos.models.course.domain.CourseImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseImgRepository extends JpaRepository<CourseImg, Long> {

    List<CourseImg> findByCourse(Course course);

    Optional<CourseImg> findFirstByCourseAndMainImgYn(Course course, Boolean mainImgYn);


}
