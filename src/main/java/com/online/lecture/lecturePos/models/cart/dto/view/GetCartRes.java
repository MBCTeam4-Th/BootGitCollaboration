package com.online.lecture.lecturePos.models.cart.dto.view;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetCartRes {
    private List<CartLineRes> lines;
    private int total;
}