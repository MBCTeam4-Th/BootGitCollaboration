package com.online.lecture.lecturePos.models.course.dto.getCouresDetail;


import com.online.lecture.lecturePos.models.course.domain.Course;
import com.online.lecture.lecturePos.models.course.domain.CourseImg;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCourseDetailRes {
    private List<CourseImg> courseImgs; // 상세화면에서 썸네일 루프돌릴때 쓸 리스트 추가
    GetCourseDetailReq courseDetail;

}
