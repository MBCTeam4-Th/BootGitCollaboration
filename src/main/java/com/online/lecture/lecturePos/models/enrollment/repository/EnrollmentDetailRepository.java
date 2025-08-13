package com.online.lecture.lecturePos.models.enrollment.repository;

import com.online.lecture.lecturePos.models.enrollment.domain.EnrollmentDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentDetailRepository extends JpaRepository<EnrollmentDetail, Long> {


}
