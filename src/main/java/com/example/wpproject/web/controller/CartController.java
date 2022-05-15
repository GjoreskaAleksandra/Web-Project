package com.example.wpproject.web.controller;

import com.example.wpproject.model.Cart;
import com.example.wpproject.model.User;
import com.example.wpproject.service.CartService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String getCartPage(@RequestParam(required = false) String error, HttpServletRequest req, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username = req.getRemoteUser();
        Cart cart = this.cartService.getActiveCart(username);
        model.addAttribute("drugs", this.cartService.listAllDrugsInCart(cart.getId()));
        model.addAttribute("bodyContent", "cart");
        return "master-template";
    }

    @PostMapping("/add-drug/{id}")
    public String addDrugToCart(@PathVariable Long id, HttpServletRequest req, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            this.cartService.addDrugToCart(user.getUsername(), id);
            return "redirect:/cart";
        } catch (RuntimeException exception) {
            return "redirect:/cart?error=" + exception.getMessage();
        }
    }
}
