package com.online.lecture.lecturePos.models.course.dto.modifyCourse;


import com.online.lecture.lecturePos.models.course.dto.getCouresDetail.GetCourseDetailReq;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModifyCourseRes {
    GetCourseDetailReq courseDetail;
    GetCourseDetailReq modifiedCourse;
}
