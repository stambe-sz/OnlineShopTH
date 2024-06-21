package onlineshop.web.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import onlineshop.model.binding.ProductAddBindingModel;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add")
    public String getAddProduct() {
        return "add-product";
    }
    @GetMapping("/all")
    public String getAllProducts() {
        return "products";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    private String addProduct(@Valid ProductAddBindingModel productAddBindingModel){
        productService.addProduct(modelMapper
                .map(productAddBindingModel, ProductServiceModel.class));
        return "redirect:add";
    }
    @ModelAttribute
    public ProductAddBindingModel productAddBindingModel(){
        return new ProductAddBindingModel();
    }
}
