package onlineshop.web.controllers;

import lombok.RequiredArgsConstructor;
import onlineshop.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/users/register")
    public String register(){
        userService.register()
        return "redirect:login";
    }

}
