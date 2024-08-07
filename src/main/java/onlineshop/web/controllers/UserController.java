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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static onlineshop.constants.ControllerPaths.POST_MAPPING_REGISTER_USER;
import static onlineshop.constants.ControllerPaths.REDIRECT_TO_LOGIN;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

//todo make all paths variables
    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }

    @GetMapping("/login")
    public String getLogin(Model model){
        return "login";
    }

    @PostMapping("/login")
    public String postLogin (@Valid @ModelAttribute UserLoginBindingModel userLoginBindingModel,
                             BindingResult bindingResult){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                userLoginBindingModel.getUsername(),
                userLoginBindingModel.getPassword()
        );
        try {
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Authentication context = SecurityContextHolder.getContext().getAuthentication();

            UserDetails loggedUser = this.getLoggedInUserDetails();
            System.out.println("Logged User is: " + loggedUser.getUsername());
            return "redirect:/";
        } catch (AuthenticationException e) {
            bindingResult.rejectValue("password", "error.loginForm", "Invalid username or password");
            return "redirect:/login";
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

    public UserDetails getLoggedInUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return (UserDetails) principal;
            }
        }
        return null;
    }
}
