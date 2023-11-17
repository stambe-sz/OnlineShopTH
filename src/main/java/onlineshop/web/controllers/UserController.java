package onlineshop.web.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import onlineshop.model.binding.UserRegisterBindingModel;
import onlineshop.model.service.UserServiceModel;
import onlineshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/users/register")
    public String register(){
        return "register";
    }
    @PostMapping("/users/register")
    public String confRegister(@Valid UserRegisterBindingModel userRegisterBindingModel){
        userService.register(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        return "redirect:login";
    }

}
