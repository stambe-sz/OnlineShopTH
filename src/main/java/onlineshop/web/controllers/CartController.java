package onlineshop.web.controllers;

import lombok.RequiredArgsConstructor;
import onlineshop.model.binding.CartItemBindingModel;
import onlineshop.model.entity.Cart;
import onlineshop.model.service.CartItemServiceModel;
import onlineshop.model.service.UserServiceModel;
import onlineshop.model.view.ProductViewModel;
import onlineshop.service.CartService;
import onlineshop.service.UserService;
import onlineshop.tools.Tools;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/cart")
@Controller
public class CartController {

    private final CartService cartService;
    private final ModelMapper modelMapper;
    private final UserService userService;


    @GetMapping("")
    public ModelAndView getCart(ModelAndView model, Tools tools) throws Exception {
        String username = tools.getLoggedUser();
        UserServiceModel foundUser = userService.getUserByUsername(username);

        if (username != null && foundUser.getCart() != null){
            List<ProductViewModel> allProducts = foundUser.getCart()
                    .getCartItems().stream()
                    .map(e -> this.modelMapper.map(e,ProductViewModel.class))
                    .collect(Collectors.toList());
            model.setViewName("cart");
            model.addObject("allProducts",allProducts);
        }else {
            model.addObject("cart", new Cart());
        }
        return model;

//            List<ProductViewModel> allProducts = this.cartService
//                    .getCartItems()
//                    .stream()
//                    .map(e -> this.modelMapper.map(e, ProductViewModel.class))
//                    .collect(Collectors.toList());
//            model.setViewName("cart");
//            model.addObject("allProducts", allProducts);
//            return model;
    }
    @GetMapping("/cart/checkout")
    public String cartCheckout(@ModelAttribute String username) {
        //...
        return null;
    }
    @PostMapping("/add-to-cart")
    public void addProductToUserCart(@ModelAttribute CartItemBindingModel cibm) throws Exception {
        this.cartService.addProductToCart(
                this.modelMapper.map(cibm, CartItemServiceModel.class)
        );
    }

}
