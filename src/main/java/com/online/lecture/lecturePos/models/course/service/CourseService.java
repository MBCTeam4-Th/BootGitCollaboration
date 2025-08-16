package com.online.lecture.lecturePos.models.course.service;

import com.online.lecture.lecturePos.models.course.dto.getCouresDetail.GetCourseDetailReq;
import com.online.lecture.lecturePos.models.course.dto.getCouresDetail.GetCourseDetailRes;
import com.online.lecture.lecturePos.models.course.dto.getCourse.GetCourseListReq;
import com.online.lecture.lecturePos.models.course.dto.getCourse.GetCourseListRes;
import com.online.lecture.lecturePos.models.course.dto.modifyCourse.ModifyCourseReq;
import com.online.lecture.lecturePos.models.course.dto.modifyCourse.ModifyCourseRes;
import com.online.lecture.lecturePos.models.course.dto.postCourse.PostCourseReq;
import com.online.lecture.lecturePos.models.course.dto.postCourse.PostCourseRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {
    //1 등록
    PostCourseRes saveCourse(PostCourseReq request, List<MultipartFile> imgFiles);

    //2-1. 강좌 목록조회 admin
    GetCourseListRes getCourseList(GetCourseListReq request, Pageable pageable);

    //2-2. 강좌 상세조회 admin
    GetCourseDetailRes getCourseDetail(Long courseId);

    // 3. 강좌 수정 admin
    ModifyCourseRes modifyCourse(ModifyCourseReq request, Long courseId, List<MultipartFile> imgFiles);
    // 4. 강좌 삭제 admin
    void deleteCourse(Long courseId);

}
