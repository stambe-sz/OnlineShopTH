package onlineshop.web.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import onlineshop.model.binding.ProductAddBindingModel;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @GetMapping("/add")
    public String getAddProduct() {
        return "add-product";
    }
    @GetMapping("/all")
    public String getAllProducts() {
        return "products";
    }

    @PostMapping("/add")
    private String addProduct(@Valid ProductAddBindingModel productAddBindingModel){
        System.out.println();
        productService.addProduct(modelMapper
                .map(productAddBindingModel, ProductServiceModel.class));
        return "redirect:home";
    }
    @ModelAttribute
    public ProductAddBindingModel productAddBindingModel(){
        return new ProductAddBindingModel();
    }
}
