package com.online.lecture.lecturePos.models.course.dto.getCouresDetail;


import com.online.lecture.lecturePos.models.course.domain.Course;
import com.online.lecture.lecturePos.models.course.domain.CourseImg;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCourseDetailRes {

    GetCourseDetailReq courseDetail;

}
