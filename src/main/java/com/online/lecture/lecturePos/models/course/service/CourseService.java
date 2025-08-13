package com.online.lecture.lecturePos.models.course.service;

import com.online.lecture.lecturePos.models.course.dto.postCourse.PostCourseReq;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {
    //1 등록
    PostCourseReq saveCourse(PostCourseReq request, List<MultipartFile> imgFiles);
}
