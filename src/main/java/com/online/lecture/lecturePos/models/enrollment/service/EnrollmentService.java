package com.online.lecture.lecturePos.models.enrollment.service;

import com.online.lecture.lecturePos.models.course.domain.Course;
import com.online.lecture.lecturePos.models.enrollment.domain.Enrollment;
import com.online.lecture.lecturePos.models.enrollment.domain.EnrollmentDetail;
import com.online.lecture.lecturePos.models.student.domain.Student;
import com.online.lecture.lecturePos.models.course.repository.CourseRepository;
import com.online.lecture.lecturePos.models.enrollment.repository.EnrollmentDetailRepository;
import com.online.lecture.lecturePos.models.enrollment.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private EnrollmentDetailRepository enrollmentDetailRepository;

    @Autowired
    private CourseRepository courseRepository;


//    public Enrollment enroll(Student student, Long courseId) {

//        Course course = courseRepository.findById(courseId).orElseThrow();
//
//        Enrollment enrollment = new Enrollment();
//
//        enrollment.setStudent(student);
//        enrollment.setEnrollmentStatus(Enrollment.OrderStatus.order);
//
//        enrollment = enrollmentRepository.save(enrollment);
//
//        EnrollmentDetail detail = new EnrollmentDetail();
//
//        detail.setEnrollment(enrollment);
//        detail.setCourse(course);
//        detail.setCourseFee(course.getCourseFee());
//
//        enrollmentDetailRepository.save(detail);
//
//        return enrollment;
//    }
//
//    public List<Enrollment> getMyEnrollments(Student student) {
//        return enrollmentRepository.findByStudent(student);
//    }

}