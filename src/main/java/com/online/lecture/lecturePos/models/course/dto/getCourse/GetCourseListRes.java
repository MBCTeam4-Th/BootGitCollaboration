package com.online.lecture.lecturePos.models.course.dto.getCourse;

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
