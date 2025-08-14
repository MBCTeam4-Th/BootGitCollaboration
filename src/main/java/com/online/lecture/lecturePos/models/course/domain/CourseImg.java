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
//todo: 오리지널 파일네임을 저장할 필드가 없으므로 가능하다면 추가
    // @Column(name = "ori_img_name")
    //private String oriImgName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cousre_img_id")
    private Long courseImgId;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "img_name")
    private String imgName;

    @Column(name = "main_img_yn")
    private Boolean mainImgYn;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

}
