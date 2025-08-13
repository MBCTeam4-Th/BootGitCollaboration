package com.online.lecture.lecturePos.models.course.service;

import com.online.lecture.lecturePos.models.course.dto.postCourseImg.PostCourseImgReq;
import com.online.lecture.lecturePos.models.course.repository.CourseImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseImgServiceImpl implements CourseImgService {
//CourseImg 엔티티 생성 및 저장
    // FileService에서 받은 파일명과 URL을 DB에 저장
    //Course와 연결관계를 설정

    private final CourseImgRepository courseImgRepository;
    private final FileService fileService;

    @Value("C:/lecture/class")
    private String uploadPath;

    @Override
    public PostCourseImgReq saveCourseImg(PostCourseImgReq request, MultipartFile file) {
        try {
            //1.원본파일명
            String oriImgName = file.getOriginalFilename();

            //2.파일저장
            String saveFileName = fileService.uploadFile(uploadPath, oriImgName, file.getBytes());
            // 사용자가 상품의 이미지를 등록했다면 저장할 경로와 파일의 이름,
            // 파일의 바이트 배열을 파일 업로드 파라미터로 uploadFile 메서드 호출

            //3.접근 가능한 URL 생성
            // 저장한 상품의 이미지 불러올 경로를 설정
            // WebMvcConfig 에서 설정함 c:\shop이므로 /images/item/를 붙여줌
            String imgUrl = "/images/courses" + saveFileName;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
