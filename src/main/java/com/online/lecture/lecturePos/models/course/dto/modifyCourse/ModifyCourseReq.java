package com.online.lecture.lecturePos.models.course.dto.modifyCourse;


import com.online.lecture.lecturePos.core.SellStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModifyCourseReq {
    private Long courseId;
    private String courseName;
    private Integer courseFee;
    private SellStatus intemSellStatus;
    private String mainImgUrl;

    private String courseDescription; // 강의설명

    private Integer maxEnrollment;      // 수강인원

    private int currentPage;
    private int pageSize;
}
