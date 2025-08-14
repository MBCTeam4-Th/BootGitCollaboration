package com.online.lecture.lecturePos.models.student.dto;

import com.online.lecture.lecturePos.models.student.domain.Student;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {

    private Long studentId;
    private String email;
    private String password;
    private String studentName;
    private Student.Role role;

    // DTO → Entity 변환
    public Student toEntity() {
        return Student.builder()
                .studentId(studentId)
                .email(email)
                .password(password)
                .studentName(studentName)
                .role(role)
                .build();
    }

    // Entity → DTO 변환
    public static StudentDTO fromEntity(Student student) {
        return StudentDTO.builder()
                .studentId(student.getStudentId())
                .email(student.getEmail())
                .studentName(student.getStudentName())
                .role(student.getRole())
                .build();
    }
}
