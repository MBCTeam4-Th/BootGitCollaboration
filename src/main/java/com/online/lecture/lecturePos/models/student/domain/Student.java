package com.online.lecture.lecturePos.models.student.domain;

import com.online.lecture.lecturePos.core.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="student") // 테이블명은 소문자 권장
@ToString
public class Student extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "student_name")
    private String studentName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;  // 사용자, 관리자 구분

    public enum Role {
        user, admin
    }
}
