package onlineshop.web.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import onlineshop.model.binding.UserRegisterBindingModel;
import onlineshop.model.service.UserServiceModel;
import onlineshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static onlineshop.constants.ControllerPaths.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/register")
    public String register(Model model){
//        if (!model.containsAttribute("userReg")){
//            model.addAttribute("userReg",new UserRegisterBindingModel());
//        }
        return "/register";
    }
    @PostMapping(POST_MAPPING_REGISTER_USER)
    public String confRegister(@Valid @ModelAttribute("userReg") UserRegisterBindingModel userReg,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model model){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userReg",userReg);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userReg",bindingResult);

            return REDIRECT_TO_REGISTER;
        }
        if (!userReg.getPassword().equals(userReg.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("passwordsNotMatch",true);
            redirectAttributes.addFlashAttribute("userReg",userReg);
        }
        userService.register(modelMapper.map(userReg, UserServiceModel.class));
        return REDIRECT_TO_LOGIN;
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
    }

}
