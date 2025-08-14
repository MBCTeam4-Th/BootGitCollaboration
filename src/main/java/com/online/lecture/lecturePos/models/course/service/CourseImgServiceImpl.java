package com.online.lecture.lecturePos.models.course.service;

import com.online.lecture.lecturePos.models.course.domain.Course;
import com.online.lecture.lecturePos.models.course.domain.CourseImg;
import com.online.lecture.lecturePos.models.course.dto.postCourseImg.PostCourseImgRes;
import com.online.lecture.lecturePos.models.course.repository.CourseImgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class CourseImgServiceImpl implements CourseImgService {
    //CourseImg 엔티티 생성 및 저장
    // FileService에서 반환 받은 파일명과 URL을 CourseImg엔티티에 저장 -> DB에 저장
    //Course와 연결관계를 설정

    private final CourseImgRepository courseImgRepository;
    private final FileService fileService;

    @Value("C:/lecture/class") // @Value("${courseImgLocation}") // application.properties에서 설정 courseImgLocation=C:/lecture/class

    private String uploadPath;

    @Override
    public PostCourseImgRes saveCourseImg(Course course, MultipartFile file, boolean isRepresentative) {
        if(file.isEmpty()){
            log.info("업로드할 파일이 없습니다.");

        }

        try {
            //1.원본파일명
            String oriImgName = file.getOriginalFilename();

            //2.파일저장
            String saveFileName = fileService.uploadFile(uploadPath, oriImgName, file.getBytes());
            // 사용자가 상품의 이미지를 등록했다면 저장할 경로와 파일의 이름,
            // uploadFile의 내부 구현(폴더 경로, 파일명 규칙, 저장 방식)이 바뀌어도 CourseImgServiceImpl는 영향을 안 받음. 다른서비스에서도 재활용가능
            // 파일의 바이트 배열을 파일 업로드 파라미터로 uploadFile 메서드 호출

            //3.접근 가능한 URL 생성
            // 저장한 상품의 이미지 불러올 경로를 설정
            // WebMvcConfig 에서 설정함 c:\shop이므로 /images/item/를 붙여줌
            String imgUrl = "/images/courses/" + saveFileName;

            //4.CourseImg 엔티티생성
            CourseImg saveCourseImg = CourseImg.builder()
                    .course(course)
                    .imgName(saveFileName) // 서버에 저장된 파일명
                    .imgUrl(imgUrl)         // 접근 가능한 URL
                    .mainImgYn(isRepresentative)
                    .build();
            var courseImg = courseImgRepository.save(saveCourseImg);

            return PostCourseImgRes.builder()
                    .courseImgDetail(courseImg)
                            .build();

            //이미지저장완료
            //log.info("CourseImg 저장 완료: courseId={}, imgName={}", course.getCourseId(), saveFileName);


        } catch (Exception e) {
            throw new RuntimeException("이미지 저장 실패", e);
        }
    }
}
