package onlineshop.web.controllers;

import lombok.RequiredArgsConstructor;
import onlineshop.model.service.CartItemServiceModel;
import onlineshop.model.service.UserServiceModel;
import onlineshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@RequiredArgsConstructor
@Controller
@RequestMapping()
public class OrderController {

    private final UserService userService;

    @GetMapping("/orders/{username}")
    public String getHomePage(@PathVariable String username) throws Exception {
        //TODO ...
        UserServiceModel foundUser =
                userService.getUserByUsername(username);
        List<CartItemServiceModel> userItems =
                foundUser.getCart().getCartItems();
        return "orders";
    }
}
