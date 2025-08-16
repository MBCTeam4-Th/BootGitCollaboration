package com.online.lecture.lecturePos.models.cart.dto.remove;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RemoveItemReq {
    @NotNull private Long studentId;
    @NotNull private Long cartItemId;
}