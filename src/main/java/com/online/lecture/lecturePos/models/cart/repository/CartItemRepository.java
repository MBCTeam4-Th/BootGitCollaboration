package com.online.lecture.lecturePos.models.cart.repository;

import com.online.lecture.lecturePos.models.cart.domain.Cart;
import com.online.lecture.lecturePos.models.cart.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCart(Cart cart);

}