package com.online.lecture.lecturePos.models.course.dto.postCourse;

import com.online.lecture.lecturePos.core.SellStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class PostCourseReq {
//프론트에서 넘어오는 객체 처리용 Course 등록
    //나중에 messages.properties로 messages 처리 추가
    @NotEmpty(message = "강의명을 입력해주세요")
    private String courseName;          //강의명

    private String courseDescription; // 강의설명

    private Integer courseFee;         //수강료

    private SellStatus intemSellStatus; //모집, 마감

    private Integer maxEnrollment;      // 수강인원

}
