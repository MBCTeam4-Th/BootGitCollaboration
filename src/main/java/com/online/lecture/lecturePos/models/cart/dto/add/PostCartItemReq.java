package com.online.lecture.lecturePos.models.cart.dto.add;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCartItemReq {
    @NotNull private Long studentId;
    @NotNull private Long courseId;
    @Min(1) private Integer quantity = 1;
}
