package onlineshop.web.controllers;

import lombok.RequiredArgsConstructor;
import onlineshop.model.entity.Product;
import onlineshop.model.service.CartItemServiceModel;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.model.service.UserServiceModel;
import onlineshop.model.view.ProductViewModel;
import onlineshop.service.CartService;
import onlineshop.service.UserService;
import org.apache.catalina.filters.RemoteIpFilter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/order")
public class OrderController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CartService cartService;

    @GetMapping
    public String getOrder() {
        return "orders";
    }

    @GetMapping("/get/{username}")
    public ModelAndView getHomePage(@PathVariable("username") String username, ModelAndView model) throws Exception {
        //TODO ...

        UserServiceModel foundUser =
                userService.getUserByUsername(username);
//        List<CartItemServiceModel> userItems =
//                foundUser.getCart().getCartItems();
        List<ProductViewModel> userCartItems = this.cartService.getUserCartItems(username);
//        List<ProductViewModel> userProducts = this.cartService.getCartItems()
//                .stream()
//                .map(e -> modelMapper.map(e, ProductViewModel.class))
//                .collect(Collectors.toList());
        Double totalAmount = 0.0;
        for (ProductViewModel userItem : userCartItems) {
            totalAmount += userItem.getPrice();
        }

        model.setViewName("orders");
        model.addObject("userCartItems", userCartItems);
        model.addObject("totalAmount", totalAmount);
        model.addObject("user", foundUser);
        return model;
    }
}
