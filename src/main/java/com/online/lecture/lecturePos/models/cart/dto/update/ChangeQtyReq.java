package com.online.lecture.lecturePos.models.cart.dto.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChangeQtyReq {
    @NotNull private Long studentId;
    @NotNull private Long cartItemId;
    @Min(1) private Integer quantity;
}