package com.online.lecture.lecturePos.models.enrollment.domain;


import com.online.lecture.lecturePos.models.course.domain.Course;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "enrollment_detail_id")
    private Long enrollmentDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "course_fee")
    private Integer courseFee;
//git test
}
