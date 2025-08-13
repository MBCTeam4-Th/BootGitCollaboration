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
public class Student extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "student_name")
    private String studentName;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        user, admin
    }

}
