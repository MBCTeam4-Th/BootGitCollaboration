package com.online.lecture.lecturePos.models.cart.repository;

import com.online.lecture.lecturePos.models.cart.domain.Cart;
import com.online.lecture.lecturePos.models.cart.domain.CartItem;
import com.online.lecture.lecturePos.models.course.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCart(Cart cart); //카트에 담긴 아이템들 전부 꺼내오기

    Optional<CartItem> findByCartAndCourse(Cart cart, Course course);
    Optional<CartItem> findByCartItemIdAndCartStudentStudentId(Long carItemId, Long studentId); //내아이템만 찾는 메서드

    long deleteByCartItemIdAndCartStudentStudentId(Long cartItemId, Long studentId); //장바구니 아이템 삭제
    long deleteByCartStudentStudentId(Long studentId); // 장바구니 전체 비우기 메서드 

}