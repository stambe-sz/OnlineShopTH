package onlineshop.web.controllers;

import lombok.RequiredArgsConstructor;
import onlineshop.model.binding.CartBindingModel;
import onlineshop.model.service.ProductServiceModel;
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
    private final Tools tools;


    @GetMapping
    public ModelAndView getCart(ModelAndView model) throws Exception {
        CartBindingModel cartObject = new CartBindingModel();
        List<ProductViewModel> allProducts = this.cartService
                .getCartItems()
                .stream()
                .map(e -> this.modelMapper.map(e, ProductViewModel.class))
                .collect(Collectors.toList());
        model.setViewName("cart");
        model.addObject("cartObject", cartObject);
        model.addObject("allProducts", allProducts);
        model.addObject("username", tools.getLoggedUser());
        return model;
    }
    @GetMapping("/all")
    public ModelAndView getAllProducts(ModelAndView model) throws Exception {
        List<ProductServiceModel> allProducts = this.cartService.getCartItems();
        List<ProductViewModel> products = allProducts.stream()
                .map(e -> this.modelMapper.map(e, ProductViewModel.class))
                .collect(Collectors.toList());
        model.addObject("products", products);
        model.setViewName("products");
        return model;
    }

    @GetMapping("/checkout")
    public String cartCheckout(@ModelAttribute String username) throws Exception {
        this.cartService.getCartItems();
        return null;
    }

    @GetMapping("/add-to-cart/{productId}")
    public String addProductToUserCart(@PathVariable("productId") Long productId, Model model) throws Exception {
        this.cartService.addProductToCart(productId);
        return "redirect:/products";
    }

    @GetMapping("/remove-from-cart/{productId}")
    public String removeFromCart(@PathVariable("productId") Long productId) throws Exception {
        this.cartService.removeProductFromCart(productId);
        return "redirect:/cart";
    }
    @ModelAttribute
    public CartBindingModel cartBindingModel(){
        return new CartBindingModel();
    }
}
