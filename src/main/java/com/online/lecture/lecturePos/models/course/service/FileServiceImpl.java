package com.online.lecture.lecturePos.models.course.service;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    // 파일 저장의 모든 책임을 담당 , 파일 저장 규칙(폴더 경로, 파일명 생성 규칙, 예외 처리)이 바뀌어도 FileServiceImpl만 수정하면 된다.
    // Course 이미지뿐 아니라 다른 엔티티(Product, Profile, Board 등)도 파일 업로드가 필요할 수 있음. 파일 업로드시 이 서비스를 호출.
    // FileServiceImpl은 파일 저장 로직만 전담하는 서비스.

    //이렇게 쪼개놓으면 관심사 분리 + 재사용성 + 유지보수성이 좋아짐.
    //다만 uploadFile 메서드에는 방어 코드, UUID, 경로 분리, 예외 처리를 꼭 넣어야 함.

    //  MultipartFile 물리적 파일 저장
    // FileServiceImpl은 물리 파일 저장까지만 책임지고, DB 저장은 CourseImgServiceImpl

    //  MultipartFile 효율적으로 처리하는 핵심 포인트
    // 원본파일명 (oriImgName)은 사용자 정보 확인용
    // 저장파일명(saveFileName)은 UUID등 랜덤 생성 -> 파일명 중복 방지
    // null & empty 체크 : 사용자가 이미지를 안올릴 수 도 있으니 isEmpty()로 체크필수

    @Override
    public String uploadFile(String uploadPath, String oriImgName, byte[] fileData) throws IllegalArgumentException {
        // 1. 사용자가 파일을 안 올린경우 방
        if(oriImgName == null || oriImgName.isEmpty()){
            throw new IllegalArgumentException("원본 파일명이 비어있습니다.");
        }
       /* try{
       원본파일명도 같이 저장되므로 직관적이지만, 원본파일명에 공백, 특수문자, 한글이 포함될경우 os환경에서 깨질 가능성 있음
       보안 측면에서 원본파일명 노출 -> 사용자가 의도적으로 특수한 파일명을 업로드하면 문자될수있음 파일명 길어질수있음
            // 2.랜덤 파일명 생성
            String uuid = UUID.randomUUID().toString(); // 랜덤문자열이 생성
            Path savePath = Paths.get(uploadPath, uuid+"_"+oriImgName); //uuid_원본파일명

            String saveFileName =
        }*/

        try {
            //2. 랜덤 UUID + 원본명 전처리
        UUID uuid = UUID.randomUUID(); //랜덤 파일명 생성
        String extension = oriImgName.substring(oriImgName.lastIndexOf(".")); // 확장자
            String safeOriName = oriImgName.replaceAll("[^a-zA-Z0-9._-]", "_");
            // 특수문자 제거 특수문자, 공백 제거 → OS 환경에서 깨지는 문제 방지

            String saveFileName = uuid + "_" + safeOriName;

            //String saveFileName = uuid+ extension; //uuid+원래파일명 결합

            //3. 저장경로구성 File.separator는 OS별 구분자 자동 처리 (윈도우 \, 리눅스 /)
            String filePath = uploadPath + File.separator + saveFileName;

            //4.실제파일저장
            FileOutputStream fos = new FileOutputStream(filePath); // FileOutputStream : 바이트 배열
            fos.write(fileData); // fos.close() 리소스해제
            fos.close();

            log.info("파일 저장 완료: {}", filePath); // {} 치환문법(SLF4J 스타일) 저장 완료 로그, DB에는 이 반환된 저장 파일명을 저장
            return saveFileName;


        }catch (Exception e) {
            throw new RuntimeException("파일 업로드 실패", e);
        }

       // return "";
    }
}
