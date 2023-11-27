package onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import onlineshop.model.entity.Category;
import onlineshop.model.entity.Product;
import onlineshop.repository.CategoryRepository;
import onlineshop.repository.ProductRepository;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.service.CategoryService;
import onlineshop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    //todo categoryService
    private final ModelMapper modelMapper;

    @Override
    public ProductServiceModel addProduct(ProductServiceModel productServiceModel) {
        Product foundProduct = this.productRepository
                .findProductByName(productServiceModel.getName()).orElse(null);
        if (foundProduct != null){
            //todo change exception
            throw new IllegalArgumentException();
        }
        Product product = modelMapper.map(productServiceModel,Product.class);
        String categoryName = productServiceModel.getCategory().getCategoryName();
        Category foundCategory = this.categoryService.findCategoryByName(categoryName);
        product.setCategory(foundCategory);
        Product savedProduct = this.productRepository.save(product);
        return modelMapper.map(savedProduct, ProductServiceModel.class);
    }

    @Override
    public ProductServiceModel editProduct(ProductServiceModel productServiceModel) {
        Product productFound = this.findProductById(productServiceModel.getId());
        Product editProduct = this.mapProduct(productFound,productServiceModel);
        this.productRepository.save(editProduct);
        return modelMapper.map(editProduct, ProductServiceModel.class);
    }
    @Override
    public boolean deleteProductById(Long productId) {
        Product product = this.findProductById(productId);
        this.productRepository.delete(product);
        return true;
    }

    @Override
    public ProductServiceModel getProductById(Long productId) {
        Product product = this.productRepository.findById(productId).orElse(null);
        return modelMapper.map(product, ProductServiceModel.class);
    }

    private Product findProductById(Long id) {
        Product product = this.productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new NoSuchElementException();
        }
        return product;
    }
    private Product mapProduct(Product productFound, ProductServiceModel productServiceModel) {
        productFound.setProductCondition(productServiceModel.getProductCondition());
        productFound.setName(productServiceModel.getName());
        productFound.setQuantity(productServiceModel.getQuantity());
        productFound.setCategory(productServiceModel.getCategory());
        productFound.setDescription(productServiceModel.getDescription());
        return productFound;
    }
}
