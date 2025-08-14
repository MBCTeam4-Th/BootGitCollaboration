package com.online.lecture.lecturePos.models.course.dto.getCourse;


import com.online.lecture.lecturePos.core.SellStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class GetCourseListReq {
    private Long courseId;      //강의번호
    private String courseName;  // 강의이름
    private Integer courseFee;  // 수강료
    private SellStatus intemSellStatus;     // 강의상태

    private String mainImageUrl; // 대표이미지 URL (나중에 연동, 지금은 null 가능)
}
