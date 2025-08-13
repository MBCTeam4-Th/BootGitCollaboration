package com.online.lecture.lecturePos.models.course.dto.postCourseImg;

import com.online.lecture.lecturePos.models.course.domain.Course;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCourseImgReq {
    private String orgImgName; //원본파일명
    private String imgUrl; //이미지조회경로
    private String imgName; //이미지파일명
    private Boolean mainImgYn; //대표이미지여부
    private Course course;

}
