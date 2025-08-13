package com.online.lecture.lecturePos.models.course.service;

import com.online.lecture.lecturePos.models.course.dto.postCourseImg.PostCourseImgReq;
import org.springframework.web.multipart.MultipartFile;

public interface CourseImgService {
    //1.파일업로드
    PostCourseImgReq saveCourseImg(PostCourseImgReq request, MultipartFile file);
}
