package com.online.lecture.lecturePos.models.enrollment.repository;

import com.online.lecture.lecturePos.models.enrollment.domain.Enrollment;
import com.online.lecture.lecturePos.models.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudent(Student student);
}
