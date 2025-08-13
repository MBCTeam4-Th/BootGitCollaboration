package com.online.lecture.lecturePos.models.course.service;

import com.online.lecture.lecturePos.models.course.domain.Course;
import com.online.lecture.lecturePos.models.course.dto.postCourseImg.PostCourseImgReq;
import com.online.lecture.lecturePos.models.course.dto.postCourseImg.PostCourseImgRes;
import org.springframework.web.multipart.MultipartFile;

public interface CourseImgService {
    //1.파일업로드
    PostCourseImgRes saveCourseImg(Course course, MultipartFile file, boolean isRepresentative);
}
