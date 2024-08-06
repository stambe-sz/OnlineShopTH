package onlineshop.web.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import onlineshop.model.binding.ProductAddBindingModel;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.model.view.ProductViewModel;
import onlineshop.service.ProductService;
import onlineshop.tools.Tools;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public ModelAndView getProducts(ModelAndView model) {
        ProductAddBindingModel addProductObject = new ProductAddBindingModel();
        List<ProductServiceModel> allProducts = this.productService.getAll();
        List<ProductViewModel> products = allProducts.stream()
                .map(e -> this.modelMapper.map(e, ProductViewModel.class))
                .collect(Collectors.toList());
        String username = this.tools.getLoggedUser();
        model.addObject("products", products);
        model.addObject("username", username);
        model.addObject("addProductObject", addProductObject);
        model.setViewName("products");
        return model;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public String addProduct(@Valid ProductAddBindingModel productAddBindingModel) {
        productService.addProduct(modelMapper.map(productAddBindingModel, ProductServiceModel.class));
        return "redirect:add";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProductById(productId);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable Long id, ModelAndView modelAndView) {
        ProductServiceModel foundProduct = productService.getProductById(id);
        ProductViewModel productForEdit = modelMapper.map(foundProduct, ProductViewModel.class);
        productService.editProduct(foundProduct);
        modelAndView.addObject("productForEdit", productForEdit);
        modelAndView.setViewName("product-edit");
        return modelAndView;
    }

//    @PostMapping("/edit/{id}")
//    public String editProduct(@PathVariable Long id,@Valid ProductEditBindingModel productEditBindingModel) {
//        productService.editProduct(modelMapper.map(productEditBindingModel,ProductServiceModel.class));
//        return "redirect:edit";
//    }

    @ModelAttribute
    public ProductAddBindingModel productAddBindingModel() {
        return new ProductAddBindingModel();
    }
}
