package onlineshop.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

public class CartController {

    @GetMapping("/cart/checkout")
    public String cartCheckout(@ModelAttribute String username) {
        //...
        return null;
    }

}
