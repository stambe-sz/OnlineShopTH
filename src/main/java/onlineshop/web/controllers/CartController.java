package onlineshop.web.controllers;

import lombok.RequiredArgsConstructor;
import onlineshop.model.binding.CartItemBindingModel;
import onlineshop.model.service.CartItemServiceModel;
import onlineshop.model.view.ProductViewModel;
import onlineshop.service.CartService;
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

    @GetMapping("")
    public ModelAndView getCart(ModelAndView model) throws Exception {
        List<ProductViewModel> allProducts = this.cartService
                .getCartItems()
                .stream()
                .map(e -> this.modelMapper.map(e, ProductViewModel.class))
                .collect(Collectors.toList());
        model.setViewName("cart");
        model.addObject("allProducts", allProducts);
        return model;
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
