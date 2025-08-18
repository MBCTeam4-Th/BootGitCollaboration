package com.online.lecture.lecturePos.models.student.service;

import com.online.lecture.lecturePos.models.student.dto.StudentDTO;
import com.online.lecture.lecturePos.models.student.domain.Student;
import com.online.lecture.lecturePos.models.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 회원가입
    public StudentDTO signUp(StudentDTO dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Student saved = studentRepository.save(dto.toEntity());
        return StudentDTO.fromEntity(saved);
    }

    // 전체 조회
    public List<StudentDTO> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(StudentDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // ID로 조회
    public StudentDTO findById(Long id) {
        return studentRepository.findById(id)
                .map(StudentDTO::fromEntity)
                .orElse(null);
    }

    // 이메일로 조회
    public StudentDTO findByEmail(String email) {
        return studentRepository.findByEmail(email)
                .map(StudentDTO::fromEntity)
                .orElse(null);
    }

    // 수정
    public StudentDTO update(StudentDTO dto) {
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        } else {
            dto.setPassword(studentRepository.findById(dto.getStudentId())
                    .map(Student::getPassword)
                    .orElse(null));
        }
        Student updated = studentRepository.save(dto.toEntity());
        return StudentDTO.fromEntity(updated);
    }

    // 삭제
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }


}