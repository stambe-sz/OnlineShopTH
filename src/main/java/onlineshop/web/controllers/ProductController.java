package onlineshop.web.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import onlineshop.model.binding.ProductAddBindingModel;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @GetMapping("/products/add")
    public String getAddProduct() {
        return "add-product";
    }
    @GetMapping("/products")
    public String getAllProducts() {
        return "products";
    }

    @PostMapping("/products/add")
    private String addProduct(@Valid ProductAddBindingModel productAddBindingModel){
        System.out.println();
        productService.addProduct(modelMapper
                .map(productAddBindingModel, ProductServiceModel.class));
        return "redirect:home";
    }

}
