package com.online.lecture.lecturePos.models.course.dto.postCourseImg;

import com.online.lecture.lecturePos.models.course.domain.CourseImg;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCourseImgRes {
    private CourseImg courseImgDetail;
}
