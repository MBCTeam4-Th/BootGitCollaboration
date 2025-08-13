package com.online.lecture.lecturePos.models.student.service;

import com.online.lecture.lecturePos.models.student.domain.Student;
import com.online.lecture.lecturePos.models.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student signUp(Student student) {

        return studentRepository.save(student);
    }

    public Student findByEmail(String email) {

        return studentRepository.findByEmail(email).orElse(null);
    }
    
}



