package onlineshop.web.controllers;

import lombok.RequiredArgsConstructor;
import onlineshop.model.entity.Product;
import onlineshop.model.service.CartItemServiceModel;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.model.service.UserServiceModel;
import onlineshop.model.view.ProductViewModel;
import onlineshop.model.view.UserViewModel;
import onlineshop.service.CartService;
import onlineshop.service.ProductService;
import onlineshop.service.UserService;
import onlineshop.tools.Tools;
import org.apache.catalina.filters.RemoteIpFilter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private final ProductService productService;
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
    public String finishOrder(@PathVariable("username") String username, Model model) throws Exception {
        List<ProductViewModel> userCart = cartService.getUserCartItems(username);
        userCart.forEach(e -> {
            ProductServiceModel foundProduct =productService.getProductById(e.getId());
            int newQuantity = foundProduct.getQuantity() - e.getQuantity();
            if (newQuantity < 0){
                throw new RuntimeException("Not enough quantity!");
            }
            foundProduct.setQuantity(newQuantity);
            productService.editProduct(foundProduct);
        });
        //1.да се изтрие кошницата на потребителя, БЕЗ да се
        //трие кощницата
        //2.да се намали количеството на всеки продукт в базата
        //3.да се запише в таблицата Order запис с информация за
        //направения ордер
        return "products";
    }
}
