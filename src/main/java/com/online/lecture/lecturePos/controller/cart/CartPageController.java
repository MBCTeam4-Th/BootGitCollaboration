package com.online.lecture.lecturePos.controller.cart;


import com.online.lecture.lecturePos.models.cart.service.CartServiceImpl;
import com.online.lecture.lecturePos.models.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartPageController {

    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private StudentService studentService;


    // 아래 코드는 예시로 작성된 pageController 입니다.
    // 전체적인 구조와 방식을 참조하시는 용도로 사용하시면 됩니다.

//    // 장바구니 전체 아이템 조회 페이지
//    @GetMapping("/my")
//    public String viewCart(HttpSession session, Model model) {
//        String email = (String) session.getAttribute("userEmail");
//        Student student = studentService.findByEmail(email);
//
//        List<CartItem> items = cartService.getCartItems(student);
//        model.addAttribute("cartItems", items);
//
//        return "cart/myCart"; // 장바구니 목록 페이지(Thymeleaf 템플릿)
//    }
//
//    // 장바구니에 강의 추가
//    @PostMapping("/add/{courseId}")
//    public String addCourseToCart(@PathVariable("courseId") Long courseId, HttpSession session) {
//        String email = (String) session.getAttribute("userEmail");
//        Student student = studentService.findByEmail(email);
//
//        cartService.addCourseToCart(student, courseId);
//
//        return "redirect:/cart/my";
//    }
//
//    // 장바구니 아이템 삭제
//    @PostMapping("/remove/{cartItemId}")
//    public String removeCourseFromCart(@PathVariable("cartItemId") Long cartItemId, HttpSession session) {
//        String email = (String) session.getAttribute("userEmail");
//        Student student = studentService.findByEmail(email);
//
//        cartService.removeCourseFromCart(student, cartItemId);
//
//        return "redirect:/cart/my";
//    }
//
//    // 장바구니 전체 비우기
//    @PostMapping("/clear")
//    public String clearCart(HttpSession session) {
//        String email = (String) session.getAttribute("userEmail");
//        Student student = studentService.findByEmail(email);
//
//        cartService.clearCart(student);
//
//        return "redirect:/cart/my";
//    }


}







