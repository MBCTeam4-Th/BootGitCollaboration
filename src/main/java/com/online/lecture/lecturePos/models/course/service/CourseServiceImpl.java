package com.online.lecture.lecturePos.models.course.service;

import com.online.lecture.lecturePos.models.course.domain.Course;
import com.online.lecture.lecturePos.models.course.domain.CourseImg;
import com.online.lecture.lecturePos.models.course.dto.getCouresDetail.GetCourseDetailReq;
import com.online.lecture.lecturePos.models.course.dto.getCouresDetail.GetCourseDetailRes;
import com.online.lecture.lecturePos.models.course.dto.getCourse.GetCourseListReq;
import com.online.lecture.lecturePos.models.course.dto.getCourse.GetCourseListRes;
import com.online.lecture.lecturePos.models.course.dto.modifyCourse.ModifyCourseReq;
import com.online.lecture.lecturePos.models.course.dto.modifyCourse.ModifyCourseRes;
import com.online.lecture.lecturePos.models.course.dto.postCourse.PostCourseReq;
import com.online.lecture.lecturePos.models.course.dto.postCourse.PostCourseRes;
import com.online.lecture.lecturePos.models.course.repository.CourseImgRepository;
import com.online.lecture.lecturePos.models.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Value("${file.upload-dir}")
    private String uploadRoot; // C:lecture/class/courses/

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
    public GetCourseListRes getCourseList(GetCourseListReq request, Pageable pageable) {

        var page = courseRepository.findAll(pageable); //Page<Course>

        Page<GetCourseListReq> mapped = page.map(c -> {
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
        //리턴타입은 GetCourseListRes이고 그안에 coursePage타입이 Page<GetCoursListReq>여야함

        return GetCourseListRes.builder()
                .coursePage(mapped) //Page<GetCourseListReq>
                .currentPage(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .build();

    }


    @Transactional(readOnly = true)
    @Override
    public GetCourseDetailRes getCourseDetail(Long courseId) {
        // 상세조회

        var course = courseRepository
                .findById(courseId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 강좌입니다 ID : " + courseId));

        String mainUrl = courseImgRepository.findByCourse(course).stream()
                .filter(img -> Boolean.TRUE.equals(img.getMainImgYn()))
                .map(CourseImg::getImgUrl)
                .findFirst()
                .orElse(null);

        GetCourseDetailReq request = GetCourseDetailReq.builder()
                .courseId(course.getCourseId())
                .courseName(course.getCourseName())
                .courseDescription(course.getCourseDescription())
                .courseFee(course.getCourseFee())
                .intemSellStatus(course.getIntemSellStatus())
                .maxEnrollment(course.getMaxEnrollment())
                .mainImageUrl(mainUrl)
                .build();

        // 전체 이미지 목록 (썸네일 루프에 사용)
        var images = courseImgRepository.findByCourse(course);

        return GetCourseDetailRes.builder()
                .courseDetail(request)
                .courseImgs(images)
                .build();
    }

    @Override
    @Transactional
    public ModifyCourseRes modifyCourse(ModifyCourseReq request, Long courseId, List<MultipartFile> newimgFiles) {
        var course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지않는 강좌입니다 ID : " + courseId));

        //1 기본 필드 업데이트
        if(request.getCourseName() !=null) course.setCourseName(request.getCourseName());
        if(request.getCourseFee() !=null) course.setCourseFee(request.getCourseFee());
        if(request.getCourseDescription() !=null) course.setCourseDescription(request.getCourseDescription());
        if(request.getIntemSellStatus() !=null) course.setIntemSellStatus(request.getIntemSellStatus());
        if(request.getMaxEnrollment() !=null) course.setMaxEnrollment(request.getMaxEnrollment());
        //if (request.getMainImgUrl() != null) course.

        //현재대표유무확인 추가
        var existImgs =courseImgRepository.findByCourse(course);
        boolean hasMain = existImgs.stream().anyMatch(img -> Boolean.TRUE.equals(img.getMainImgYn()));

        //새 이미지가 있으면 추가 저장 (대표이미지 교체는 정책 정해지면 별도 처리)
        if(newimgFiles !=null) {
            boolean mainAssigned = hasMain; // 대표있으면 true로 시작
            for(MultipartFile file : newimgFiles) {
                if(file !=null && !file.isEmpty()) {
                    boolean makeMain = !mainAssigned; // 대표이미지가 없으면 첫 유효파일을 대표이미지로
                    courseImgService.saveCourseImg(course, file, makeMain);
                    if(makeMain) mainAssigned = true;
                }
            }
        }

        // 대표이미지 재지정 (request.getMainImgUrl()에 담겨옴) 요청으로 넘어오는 경우는 우선
        if (request.getMainImgUrl() !=null && !request.getMainImgUrl().isBlank()) {
            var imgs = courseImgRepository.findByCourse(course);
            //false 모두
            imgs.forEach( i -> i.setMainImgYn(false));

            // URL일치하는것만 true
            imgs.stream()
                    .filter(i -> request.getMainImgUrl().equals(i.getImgUrl()))
                    .findFirst()
                    .ifPresent(i -> i.setMainImgYn(true));
        }


        String mainUrl = courseImgRepository.findByCourse(course).stream()
                .filter(img -> Boolean.TRUE.equals(img.getMainImgYn()))
                .map(CourseImg::getImgUrl)
                .findFirst()
                .orElse(null);

        GetCourseDetailReq detail = GetCourseDetailReq.builder()
                .courseId(course.getCourseId())
                .courseName(course.getCourseName())
                .courseDescription(course.getCourseDescription())
                .courseFee(course.getCourseFee())
                .intemSellStatus(course.getIntemSellStatus())
                .maxEnrollment(course.getMaxEnrollment())
                .mainImageUrl(mainUrl)
                .build();

        return ModifyCourseRes.builder()
                .modifiedCourse(detail)
                .build();
    }


    @Override
    @Transactional
    public void deleteCourse(Long courseId) {
        // 삭제메서드
        var course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지않는 강좌 입니다 ID : " + courseId));

        //1 자식 이미지 먼저 삭제 (필요시 파일도)
        var imgs = courseImgRepository.findByCourse(course);
        imgs.forEach(img -> {
            // 물리적 파일도 같이 삭제 수정 완료 후 해보기
            // fileService.deleteFile(uploadRoot + "/courses" + img.getImgName());
            courseImgRepository.delete(img);
        });
        //2 강좌삭제
        courseRepository.delete(course);
    }



} // course imgService 종료
