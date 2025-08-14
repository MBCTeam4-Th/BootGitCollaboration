package com.online.lecture.lecturePos.models.course.service;

import com.online.lecture.lecturePos.models.course.domain.Course;
import com.online.lecture.lecturePos.models.course.domain.CourseImg;
import com.online.lecture.lecturePos.models.course.dto.getCouresDetail.GetCourseDetailReq;
import com.online.lecture.lecturePos.models.course.dto.getCouresDetail.GetCourseDetailRes;
import com.online.lecture.lecturePos.models.course.dto.getCourse.GetCourseListReq;
import com.online.lecture.lecturePos.models.course.dto.getCourse.GetCourseListRes;
import com.online.lecture.lecturePos.models.course.dto.postCourse.PostCourseReq;
import com.online.lecture.lecturePos.models.course.dto.postCourse.PostCourseRes;
import com.online.lecture.lecturePos.models.course.repository.CourseImgRepository;
import com.online.lecture.lecturePos.models.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseImgRepository courseImgRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private CourseImgService courseImgService;

    // course service 시작
    public List<Course> listAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourse(Long courseId) {
        return courseRepository.findById(courseId);
    }
    // course service 종료

    // course imgService (대표이미지 관련)
    public List<CourseImg> getCourseImages(Course course) {
        return courseImgRepository.findByCourse(course);
    }


    @Override
    public PostCourseRes saveCourse(PostCourseReq request, List<MultipartFile> imgFiles) {
        //1.엔티티 생성, 저장

        Course course = Course.builder()
                .courseName(request.getCourseName())
                .courseDescription(request.getCourseDescription())
                .courseFee(request.getCourseFee())
                .intemSellStatus(request.getIntemSellStatus())
                .maxEnrollment(request.getMaxEnrollment())
                .build();
        var saveCourse = courseRepository.save(course);

        //2. 이미지저장 파일 저장 로직 호출(FileService)

        for (int i = 0; i < imgFiles.size(); i++) {
            courseImgService.saveCourseImg(course, imgFiles.get(i), i == 0);
            // isRepresentative 값을 엔티티의 repimgYn 같은 필드에 저장하게 해야함
        }
        return PostCourseRes.builder()
                .courseDetail(saveCourse)
                .build();

    }

    @Override
    @Transactional(readOnly = true)
    public GetCourseListRes getCourseList(Page<GetCourseListReq> request, Pageable pageable) {
        // 목록조회
        // JPA에서 엔티티 Page 가져와서 화면 DTO(GetCourseListReq)로 매핑

        var page = courseRepository.findAll(pageable);

        Page<GetCourseListReq> mapped = page.map(c -> {
                //대표 이미지 URL 없으면 null
            /*
            String mainUrl = courseImgRepository.findFirstByCourseAndMainImgYn(c)
                    .map(CourseImg::getImgUrl)
                    .orElse(null);*/
            String mainUrl = courseImgRepository.findByCourse(c).stream()
                    .filter(img -> Boolean.TRUE.equals(img.getMainImgYn()))
                    .map(CourseImg::getImgUrl)
                    .findFirst()
                    .orElse(null);

            return new GetCourseListReq(
                    c.getCourseId(),
                    c.getCourseName(),
                    c.getCourseFee(),
                    c.getIntemSellStatus(),
                    mainUrl
            );
        });

        return GetCourseListRes.builder()
                .coursePage(mapped)
                .currentPage(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .build();
    }
    @Transactional(readOnly = true)
    @Override
    public GetCourseDetailRes getCourseDetail(Long courseId) {
        // 상세조회
        Course request = courseRepository
                .findById(courseId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 강좌입니다 ID : " + courseId));

        return null;
    }

} // course imgService 종료
