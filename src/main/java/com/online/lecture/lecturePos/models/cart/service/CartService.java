package com.online.lecture.lecturePos.models.cart.service;
import com.online.lecture.lecturePos.models.cart.domain.CartItem;
import com.online.lecture.lecturePos.models.cart.dto.view.GetCartRes;


import java.util.List;

public interface CartService {

        // 1. 장바구니 과목 담기 (이미 있으면 수량 +)  c
        void addCourseToCart(Long studentId, Long courseId, Integer quantity);

        // 2.  장바구니 아이템 전체 조회  r
        List<CartItem> getCartItems(Long studentId);

        // 3. 장바구니 아이템 수량 변경  u
        void changeQuantity(Long studentId, Long cartItemId, int quantity);

        // 4. 장바구니에서 특정 아이템 삭제 d
        void removeItem(Long studentId, Long cartItemId);

       // 4-1.장바구니 전체 비우기 dd
        void clearCart(Long studentId);

        GetCartRes getCartView(Long studentId); //화면용 dto 리턴하는 메서드
}