package onlineshop.web.controllers;

import lombok.RequiredArgsConstructor;
import onlineshop.model.service.CartItemServiceModel;
import onlineshop.model.service.OrderServiceModel;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.model.service.UserServiceModel;
import onlineshop.model.view.ProductViewModel;
import onlineshop.model.view.UserViewModel;
import onlineshop.service.CartService;
import onlineshop.service.OrderService;
import onlineshop.service.ProductService;
import onlineshop.service.UserService;
import onlineshop.tools.Tools;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/order")
public class OrderController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CartService cartService;
    private final ProductService productService;
    private final OrderService orderService;
    private final Tools tools;

    @GetMapping
    public String getOrder() {
        return "orders";
    }

    @GetMapping("/get")
    public ModelAndView getOrders(ModelAndView model) throws Exception {
        List<ProductViewModel> allOrderedProducts =
                this.cartService.getCartItems()
                        .stream().map(pr -> this.modelMapper.map(pr, ProductViewModel.class))
                        .toList();
        double totalAmount = 0;
        for (ProductViewModel product : allOrderedProducts) {
            totalAmount += product.getPrice() * product.getOrderQuantity();
        }

        UserViewModel foundUser =
                this.modelMapper.map(userService.getUserByUsername(
                        this.tools.getLoggedUser()),
                        UserViewModel.class);

        model.setViewName("orders");
        model.addObject("allOrderedProducts", allOrderedProducts);
        model.addObject("totalAmount", totalAmount);
        model.addObject("user", foundUser);
        return model;
    }

    @GetMapping("/finish-order")
    public String finishOrder( Model model) throws Exception {
        String username = tools.getLoggedUser();
        UserServiceModel user = userService.getUserByUsername(username);
        List<CartItemServiceModel> userCart = user.getCart().getCartItems();
        userCart.forEach(e -> {
            ProductServiceModel foundProduct =productService.getProductById(e.getId());
            int newQuantity = foundProduct.getQuantity() - e.getQuantity();
            foundProduct.setQuantity(newQuantity);
            productService.editProduct(foundProduct);
        });
        List<String> productsIds = new ArrayList<>();
        userCart.forEach(p -> {
            productsIds.add(String.format("%s#%s",
                    p.getProductId(), p.getQuantity()));
        });
        OrderServiceModel newOrder = new OrderServiceModel(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                "new",
                "",
                username,
                productsIds
        );
        orderService.create(newOrder);
        user.getCart().getCartItems().clear();
        userService.saveUserToDb(user);
        return "products";
    }
}
