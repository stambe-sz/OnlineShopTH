package onlineshop.web.controllers;

import lombok.RequiredArgsConstructor;
import onlineshop.model.entity.Product;
import onlineshop.model.service.CartItemServiceModel;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.model.service.UserServiceModel;
import onlineshop.model.view.ProductViewModel;
import onlineshop.service.UserService;
import org.apache.catalina.filters.RemoteIpFilter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping()
public class OrderController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/orders/{username}")
    public ModelAndView getHomePage(@PathVariable String username, ModelAndView model) throws Exception {
        //TODO ...
        UserServiceModel foundUser =
                userService.getUserByUsername(username);
        List<CartItemServiceModel> userItems =
                foundUser.getCart().getCartItems();
        Double totalAmount = 0.0;
        for (CartItemServiceModel userItem : userItems) {
            totalAmount += userItem.getPrice();
        }
        List<ProductViewModel> userProducts =
                userItems.stream()
                        .map(e -> modelMapper.map(e, ProductViewModel.class))
                        .collect(Collectors.toList());
        model.setViewName("orders");
        model.addObject("userProducts", userProducts);
        model.addObject("totalAmount", totalAmount);
        model.addObject("user", foundUser);
        return model;
    }
}
