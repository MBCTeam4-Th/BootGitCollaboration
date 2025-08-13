package com.online.lecture.lecturePos.models.cart.repository;

import com.online.lecture.lecturePos.models.cart.domain.Cart;
import com.online.lecture.lecturePos.models.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByStudent(Student student);

}
