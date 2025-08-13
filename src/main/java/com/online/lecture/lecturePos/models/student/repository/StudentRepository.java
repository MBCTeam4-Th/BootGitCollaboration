package com.online.lecture.lecturePos.models.student.repository;

import com.online.lecture.lecturePos.models.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);
}
