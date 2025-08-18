package com.online.lecture;

import com.online.lecture.lecturePos.models.student.domain.Student;
import com.online.lecture.lecturePos.models.student.dto.StudentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LectureApplicationTests {

    @Test
    void contextLoads() {
        StudentDTO dto = StudentDTO.builder()
                .studentId(1L)
                .email("test@test.com")
                .password("1234")
                .studentName("홍길동")
                .role(Student.Role.user)
                .build();

        // 이게 어떤 클래스에서 로딩되는지 출력
        System.out.println(dto.getClass());
        // Lombok이 만든 getter가 정상 작동하는지도 확인
        System.out.println(dto.getEmail());
    }
}