package onlineshop.web.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import onlineshop.model.binding.ProductAddBindingModel;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @GetMapping("/products")
    public String getAllProducts() {
        return "All Products";
    }
    @PostMapping("/products/add")
    private String addProduct(@Valid ProductAddBindingModel productAddBindingModel){
        productService.addProduct(modelMapper
                .map(productAddBindingModel, ProductServiceModel.class));
        return "redirect:home";
    }

}
