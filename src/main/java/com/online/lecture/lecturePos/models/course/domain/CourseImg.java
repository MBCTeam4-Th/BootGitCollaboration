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
    private String imgUrl; //이미지조회경로

    @Column(name = "img_name")
    private String imgName; //이미지파일명

    @Column(name = "main_img_yn") // todo: Boolean이라 null 저장 가능 → 대표이미지 여부 필수면 @Column(nullable = false) 가능하다면 추가 ,nullable = false
    private Boolean mainImgYn; //대표이미지여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id") // Course와 다대일 단방향 관계로 매핑
    private Course course;

}
