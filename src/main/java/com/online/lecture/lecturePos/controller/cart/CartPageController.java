package com.online.lecture.lecturePos.controller.cart;



import com.online.lecture.lecturePos.models.cart.dto.add.PostCartItemReq;
import com.online.lecture.lecturePos.models.cart.dto.remove.RemoveItemReq;
import com.online.lecture.lecturePos.models.cart.dto.update.ChangeQtyReq;
import com.online.lecture.lecturePos.models.cart.dto.view.GetCartRes;
import com.online.lecture.lecturePos.models.cart.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartPageController {

    private final CartService cartService;

    /** 장바구니 화면 */
    @GetMapping
    public String view(@RequestParam Long studentId, Model model) {
        GetCartRes res = cartService.getCartView(studentId);
        model.addAttribute("res", res);
        model.addAttribute("studentId", studentId);
        return "cart/view";
    }

    /** 담기 */
    @PostMapping("/add")
    public String add(@ModelAttribute @Valid PostCartItemReq req) {
        cartService.addCourseToCart(req.getStudentId(), req.getCourseId(), req.getQuantity());
        return "redirect:/cart?studentId=" + req.getStudentId();
    }

    /** 수량 변경 */
    @PostMapping("/update")
    public String update(@ModelAttribute @Valid ChangeQtyReq req) {
        cartService.changeQuantity(req.getStudentId(), req.getCartItemId(), req.getQuantity());
        return "redirect:/cart?studentId=" + req.getStudentId();
    }

    /** 아이템 삭제 */
    @PostMapping("/remove")
    public String remove(@ModelAttribute @Valid RemoveItemReq req) {
        cartService.removeItem(req.getStudentId(), req.getCartItemId());
        return "redirect:/cart?studentId=" + req.getStudentId();
    }

    /** 전체 비우기 */
    @PostMapping("/clear")
    public String clear(@RequestParam Long studentId) {
        cartService.clearCart(studentId);
        return "redirect:/cart?studentId=" + studentId;
    }
}