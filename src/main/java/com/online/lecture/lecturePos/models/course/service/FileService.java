package com.online.lecture.lecturePos.models.course.service;


public interface FileService {

    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws IllegalAccessException;

    public String deleteFile(String uploadPath, String originalFileName) throws IllegalAccessException;

}
