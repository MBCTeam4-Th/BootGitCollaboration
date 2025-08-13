package com.online.lecture.lecturePos.models.cart.service;

import com.online.lecture.lecturePos.models.cart.domain.Cart;
import com.online.lecture.lecturePos.models.cart.repository.CartItemRepository;
import com.online.lecture.lecturePos.models.cart.repository.CartRepository;

import com.online.lecture.lecturePos.models.course.repository.CourseRepository;

import com.online.lecture.lecturePos.models.student.domain.Student;
import com.online.lecture.lecturePos.models.student.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    // 학생별 장바구니 조회(없으면 새로 생성)
    public Cart getOrCreateCart(Student student) {

        return cartRepository.findByStudent(student)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setStudent(student);
                    return cartRepository.save(cart);
                });
    }

    // 아래는 service 예시입니다. 코드 구조와 logic만 참고하시고 실제 dto 설계에 따라
    // 작성 필요합니다.

//    // 장바구니에 강의 추가
//    public void addCourseToCart(Student student, Long courseId) {
//        Cart cart = getOrCreateCart(student);
//        Course course = courseRepository.findById(courseId).orElseThrow();
//
//        // 이미 담긴 아이템인지 확인
//        CartItem item = cart.getItems().stream()
//                .filter(i -> i.getCourse().getCourseId().equals(courseId))
//                .findFirst().orElse(null);
//
//        if (item != null) {
//            // 이미 있으면 수량 +1
//            item.setQuantity(item.getQuantity() + 1);
//            cartItemRepository.save(item);
//        } else {
//            // 없으면 새로 추가
//            CartItem cartItem = CartItem.builder()
//                    .cart(cart)
//                    .course(course)
//                    .quantity(1)
//                    .courseFee(course.getCourseFee())
//                    .build();
//            cartItemRepository.save(cartItem);
//            cart.getItems().add(cartItem);
//            cartRepository.save(cart);
//        }
//    }
//
//    // 장바구니 아이템 전체 조회
//    public List<CartItem> getCartItems(Student student) {
//        Cart cart = getOrCreateCart(student);
//        return cartItemRepository.findByCart(cart);
//    }
//
//    // 장바구니에서 강의(아이템) 삭제
//    public void removeCourseFromCart(Student student, Long cartItemId) {
//        Cart cart = getOrCreateCart(student);
//        CartItem item = cartItemRepository.findById(cartItemId).orElseThrow();
//        if (item.getCart().getCartId().equals(cart.getCartId())) {
//            cartItemRepository.delete(item);
//        }
//    }
//
//    // 장바구니 전체 비우기
//    public void clearCart(Student student) {
//        Cart cart = getOrCreateCart(student);
//        cartItemRepository.deleteAll(cartItemRepository.findByCart(cart));
//    }



}





