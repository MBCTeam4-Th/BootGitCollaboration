package com.online.lecture.lecturePos.models.course.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log
public class FileServiceImpl implements FileService {
    // 파일 저장의 모든 책임을 담당

    @Override
    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) {
        try {
        UUID uuid = UUID.randomUUID(); //랜덤 파일명 생성
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 확장자
            String saveFileName = uuid.toString() + extension; //uuid+원래파일명 결합



        }catch (Exception e) {}

        return "";
    }
}
