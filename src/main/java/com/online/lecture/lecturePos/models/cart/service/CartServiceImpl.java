package com.online.lecture.lecturePos.models.cart.service;
import com.online.lecture.lecturePos.models.cart.domain.Cart;
import com.online.lecture.lecturePos.models.cart.domain.CartItem;
import com.online.lecture.lecturePos.models.cart.dto.view.CartLineRes;
import com.online.lecture.lecturePos.models.cart.dto.view.GetCartRes;
import com.online.lecture.lecturePos.models.cart.repository.CartItemRepository;
import com.online.lecture.lecturePos.models.cart.repository.CartRepository;
import com.online.lecture.lecturePos.models.course.domain.Course;
import com.online.lecture.lecturePos.models.course.repository.CourseRepository;
import com.online.lecture.lecturePos.models.student.domain.Student;
import com.online.lecture.lecturePos.models.student.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {


    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    //학생 장바구니 조회. 장바구니가 있으면 가져오고 없으면 새로 만들어서 DB저장 한뒤 이걸 돌려준다. 메서드
    private Cart getOrCreateCart(Student student) {
        Optional<Cart> optional = cartRepository.findByStudent(student);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            Cart newCart = Cart.builder()
                    .student(student)
                    .build();
            return cartRepository.save(newCart);
        }
    }

    // 1. 장바구니 과목 담기 (이미 있으면 수량 +)  c
    @Override
    public void addCourseToCart(Long studentId, Long courseId, Integer quantity) {

        Student student = studentRepository.findById(studentId) //학생조회. 없으면 예외처리
                .orElseThrow(() -> new EntityNotFoundException("학생 없음: " + studentId));
        Cart cart = getOrCreateCart(student); //학생카트 가져오기. 없으면 새로 만들어서 저장후 가져오기

        Course course = courseRepository.findById(courseId) //과목조회 : 없으면 예외
                .orElseThrow(() -> new EntityNotFoundException("과목없음:" + courseId));

        int addQty = (quantity == null || quantity <= 0) ? 1 : quantity; //수량이 null이거나 0,음수이면 1로간주

        var existing = cartItemRepository.findByCartAndCourse(cart, course); //동일과목이 카트에있는지 DB조회
        if (existing.isPresent()) { //이미 있으면: 기존 수량에 추가
            CartItem item = existing.get();
            item.setQuantity(item.getQuantity() + addQty);
             } else { //없으면 새 CartItem 만들어서 저장
                CartItem newItem = CartItem.builder()
                        .cart(cart)
                        .course(course)
                        .quantity(addQty)
                        .build();
                cartItemRepository.save(newItem);
            }
    }

    // 2.  장바구니 아이템 전체 조회  r
    @Override
    @Transactional(readOnly = true) //이메서드는 읽기용도
    public List<CartItem> getCartItems(Long studentId) {
        Student student = studentRepository.findById(studentId) //학생조회. 없으면 예외처리
                .orElseThrow(() -> new EntityNotFoundException("학생 없음: " + studentId));

        //카트가 있으면 아이템들 리스트, 카트가 없으면 빈 리스트를 돌려줌
        return cartRepository.findByStudent(student)
                .map(cartItemRepository::findByCart)
                .orElse(List.of());
    }

    // 3. 장바구니 아이템 수량 변경  u
    @Override
    @Transactional
    public void changeQuantity(Long studentId, Long cartItemId, int quantity) {

        // 1): 유효성 검사. 1미만이면 거부
        if(quantity <=0 ){ throw  new IllegalArgumentException("수량은 1 이상이어야 합니다.");}

        //2): 내소유의 아이템을 한방에 조회. 없으면 권한없음예외 처리
        CartItem item = cartItemRepository
                .findByCartItemIdAndCartStudentStudentId(cartItemId, studentId)
                .orElseThrow(() -> new IllegalStateException("권한없음 또는 카트 아이템없음" + cartItemId));
       
         //3): 수량변경
          item.setQuantity(quantity);
    }

    // 4. 장바구니에서 특정 아이템 삭제 d
    @Override
    public void removeItem(Long studentId, Long cartItemId) {

        long deleted = cartItemRepository.deleteByCartItemIdAndCartStudentStudentId(cartItemId, studentId);
            if(deleted == 0) {
                throw new IllegalStateException("권한 없음 또는 카트 아이템 없음" + cartItemId);
                }
            }

    //4-1. 전체 비우기
    @Override
    public void clearCart(Long studentId) {
    cartItemRepository.deleteByCartStudentStudentId(studentId);
    }

    @Transactional(readOnly = true)
    @Override
    public GetCartRes getCartView(Long studentId) {
        var items = getCartItems(studentId); // 기존 엔티티 조회 재사용

        var lines = items.stream().map(ci ->
                new CartLineRes(
                        ci.getCartItemId(),
                        ci.getCourse().getCourseId(),
                        ci.getCourse().getCourseName(),
                        ci.getCourse().getCourseFee(),
                        ci.getQuantity(),
                        ci.getCourse().getCourseFee() * ci.getQuantity()
                )
        ).toList();

        int total = lines.stream().mapToInt(CartLineRes::getLineTotal).sum();
        return new GetCartRes(lines, total);
    }
}

