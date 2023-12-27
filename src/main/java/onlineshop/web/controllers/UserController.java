package onlineshop.web.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import onlineshop.model.binding.UserLoginBindingModel;
import onlineshop.model.binding.UserRegisterBindingModel;
import onlineshop.model.service.UserServiceModel;
import onlineshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static onlineshop.constants.ControllerPaths.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }

    @GetMapping("/login")
    public String getLogin(Model model){
        return "login";
    }

    @PostMapping("/login")
    public String postLogin (@ModelAttribute UserLoginBindingModel userLoginBindingModel,
                             BindingResult bindingResult){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                userLoginBindingModel.getUsername(),
                userLoginBindingModel.getPassword()
        );
        System.out.println();
        try {
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Authentication context = SecurityContextHolder.getContext().getAuthentication();
            return "redirect:/";
        } catch (AuthenticationException e) {
            bindingResult.rejectValue("password", "error.loginForm", "Invalid username or password");
            return "redirect:/users/login";
        }
    }

    @PostMapping(POST_MAPPING_REGISTER_USER)
    public String confRegister(@Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model model){
//        if (bindingResult.hasErrors()){
//            redirectAttributes.addFlashAttribute("userRegisterBindingModel",userRegisterBindingModel);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",bindingResult);
//
//            return REDIRECT_TO_REGISTER;
//        }
//        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
//            redirectAttributes.addFlashAttribute("passwordsNotMatch",true);
//            redirectAttributes.addFlashAttribute("userRegisterBindingModel",userRegisterBindingModel);
//        }
        userService.register(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        return REDIRECT_TO_LOGIN;
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
    }
    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel(){
        return new UserLoginBindingModel();
    }

}
