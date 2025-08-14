package com.online.lecture.lecturePos.models.course.dto.getCourse;

import com.online.lecture.lecturePos.models.course.domain.Course;
import com.online.lecture.lecturePos.models.course.domain.CourseImg;
import lombok.*;
import org.springframework.data.domain.Page;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCourseListRes {
    private Page<GetCourseListReq> coursePage;
    private int currentPage;
    private int pageSize;
}
