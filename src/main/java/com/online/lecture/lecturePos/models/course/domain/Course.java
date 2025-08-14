package com.online.lecture.lecturePos.models.course.domain;

import com.online.lecture.lecturePos.core.SellStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="course_id")
    private Long courseId;

    @Column(name ="course_name")
    private String courseName;

    @Column(name="course_description")
    private String courseDescription;

    @Column(name = "course_fee")
    private Integer courseFee;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_sell_status")
    private SellStatus intemSellStatus;

    @Column(name = "max_enrollment")
    private Integer maxEnrollment;

//    @OneToMany(mappedBy = "course")
//    private List<CourseImg> images = new ArrayList<>();
}