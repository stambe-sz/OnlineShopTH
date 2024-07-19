package onlineshop.web.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import onlineshop.model.binding.ProductAddBindingModel;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.model.view.ProductViewModel;
import onlineshop.service.ProductService;
import onlineshop.tools.Tools;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final Tools tools;

    @GetMapping("")
    public ModelAndView getProducts(ModelAndView model) {
        List<ProductServiceModel> allProducts = this.productService.getAll();
        List<ProductViewModel> products = allProducts.stream()
                .map(e -> this.modelMapper.map(e, ProductViewModel.class))
                .collect(Collectors.toList());
        String username = this.tools.getLoggedUser();
        model.addObject("products", products);
        model.addObject("username", username);
        model.setViewName("products");
        return model;
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add")
    public String getAddProduct() {
        return "product-add";
    }

    @GetMapping("/all")
    public ModelAndView getAllProducts(ModelAndView model) {
        List<ProductServiceModel> allProducts = this.productService.getAll();
        List<ProductViewModel> products = allProducts.stream()
                .map(e -> this.modelMapper.map(e, ProductViewModel.class))
                .collect(Collectors.toList());
        model.addObject("products", products);
        model.setViewName("products");
        return model;
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
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
