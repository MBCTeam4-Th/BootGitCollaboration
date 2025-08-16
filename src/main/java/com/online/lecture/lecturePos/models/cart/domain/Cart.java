package com.online.lecture.lecturePos.models.cart.domain;


import com.online.lecture.lecturePos.core.BaseEntity;
import com.online.lecture.lecturePos.models.student.domain.Student;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart", uniqueConstraints = { @UniqueConstraint(name = "uk_cart_student", columnNames = "student_id")})   // 학생 1명당 카트 1개를 DB 레벨에서도 보장
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Cart extends BaseEntity { //TODO: BaseEntity 상속 . 공통시간 기록

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //mariaDB에서는 Auto보다 더 안전 
    @Column(name = "cart_id")
    private Long cartId;

    //학생1명이 장바구니1개만 이용가능  학생 1명 ↔ 카트 1개
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false, updatable = false)
    private Student student;

    // 장바구니에 담긴 아이템들
    @Builder.Default
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

}
