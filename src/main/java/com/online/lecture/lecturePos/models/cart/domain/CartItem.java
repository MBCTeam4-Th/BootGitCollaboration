package com.online.lecture.lecturePos.models.cart.domain;


import com.online.lecture.lecturePos.models.course.domain.Course;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_item_id")
    private Long cartItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    // 추가 수량(강의면 보통 1이나, 여러개 담을 수 있게 확장 가능)
    @Column(name = "quantity")
    private Integer quantity;

    // 해당 아이템 가격(변동 가능시 분리)
    @Column(name = "course_fee")
    private Integer courseFee;

}



