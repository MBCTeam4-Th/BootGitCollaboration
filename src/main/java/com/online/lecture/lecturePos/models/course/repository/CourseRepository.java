package com.online.lecture.lecturePos.models.course.repository;


import com.online.lecture.lecturePos.models.course.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
