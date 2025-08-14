package com.online.lecture.lecturePos.models.course.dto.getCouresDetail;

import com.online.lecture.lecturePos.core.SellStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor @Builder
public class GetCourseDetailReq {
    private Long courseId;  //강의번호
    private String courseName;  //강좌명
    private String courseDescription;    // 강좌
    private Integer courseFee;           // 강의료
    private SellStatus intemSellStatus;  // 강좌상태
    private Integer maxEnrollment;       //수강인원

    //대표이미지URL
    private String mainImageUrl;
}
