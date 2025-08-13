package com.online.lecture.lecturePos.models.course.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseImg {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cousre_img_id")
    private Long courseImgId;

    @Column(name = "img_url")
    private String imgUrl; //이미지조회경로

    @Column(name = "img_name")
    private String imgName; //이미지파일명

    @Column(name = "main_img_yn")
    private Boolean mainImgYn; //대표이미지여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id") // Course와 다대일 단방향 관계로 매핑
    private Course course;

}
