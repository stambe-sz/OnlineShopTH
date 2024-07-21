package onlineshop.web.controllers;

import lombok.RequiredArgsConstructor;
import onlineshop.model.view.ProductViewModel;
import onlineshop.service.CartService;
import onlineshop.service.ProductService;
import onlineshop.service.UserService;
import onlineshop.tools.Tools;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final ProductService productService;


    @GetMapping("")
    public ModelAndView getCart(ModelAndView model, Tools tools) throws Exception {

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
    @GetMapping("/add-to-cart/{productId}")
    public String addProductToUserCart(@PathVariable("productId") long productId, Model model) throws Exception {
        this.cartService.addProductToCart(productId);
        return "redirect:/products";
    }
}
