package com.online.lecture.lecturePos.models.course.dto.postCourse;

import com.online.lecture.lecturePos.models.course.domain.Course;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCourseRes {

    Course courseDetail;
}
