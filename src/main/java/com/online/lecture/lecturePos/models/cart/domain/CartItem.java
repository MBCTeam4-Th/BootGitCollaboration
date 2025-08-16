package com.online.lecture.lecturePos.models.cart.domain;


import com.online.lecture.lecturePos.core.BaseEntity;
import com.online.lecture.lecturePos.models.course.domain.Course;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_item", uniqueConstraints = { @UniqueConstraint(name = "uk_cartitem_cart_course", columnNames = {"cart_id", "course_id"})}) //같은 카트에서 같은 과목 중복 담지 않기
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //mariaDB에서는 Auto보다 더 안전
    @Column(name = "cart_item_id")
    private Long cartItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    // 추가 수량(강의면 보통 1이나, 여러개 담을 수 있게 확장 가능)
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    // 해당 아이템 가격(변동 가능시 분리)
    @Column(name = "course_fee")
    private Integer courseFee;

    @PrePersist
    public void prePersist() {
        if (this.quantity == null || this.quantity <= 0) this.quantity = 1; // 기본값 1로
    }

}



