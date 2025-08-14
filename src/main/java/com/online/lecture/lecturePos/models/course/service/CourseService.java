package com.online.lecture.lecturePos.models.course.service;

import com.online.lecture.lecturePos.models.course.dto.postCourse.PostCourseReq;
import com.online.lecture.lecturePos.models.course.dto.postCourse.PostCourseRes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {
    //1 등록
    PostCourseRes saveCourse(PostCourseReq request, List<MultipartFile> imgFiles);
}
