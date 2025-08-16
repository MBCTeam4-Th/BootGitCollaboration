package com.online.lecture.lecturePos.models.cart.dto.view;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartLineRes {
    private Long cartItemId;
    private Long courseId;
    private String title;     // Course의 제목 필드명에 맞춰 바꾸기 (예: courseName)
    private int price;        // 단가 (예: courseFee)
    private int quantity;
    private int lineTotal;    // price * quantity
}