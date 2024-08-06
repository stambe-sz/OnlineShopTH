package onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import onlineshop.model.entity.Category;
import onlineshop.model.entity.Product;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.repository.ProductRepository;
import onlineshop.service.CategoryService;
import onlineshop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
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
        ProductServiceModel productFound = this.findProductById(productServiceModel.getId());
        ProductServiceModel editProduct = this.mapProduct(productFound,productServiceModel);
        this.productRepository.save(this.modelMapper.map(editProduct, Product.class));
        return modelMapper.map(editProduct, ProductServiceModel.class);
    }
    @Override
    public boolean deleteProductById(Long productId) {
        ProductServiceModel product = this.findProductById(productId);
        this.productRepository.delete(this.modelMapper.map(product, Product.class));
        return true;
    }

    @Override
    public ProductServiceModel getProductById(Long productId) {
        Product product = this.productRepository.findById(productId).orElse(null);
        return modelMapper.map(product, ProductServiceModel.class);
    }

    @Override
    public List<ProductServiceModel> getAll() {
        List<Product> products = this.productRepository.findAll();
        List<ProductServiceModel> foundProducts = products.stream()
                .map(p -> modelMapper.map(p,ProductServiceModel.class) )
                .toList();
        return foundProducts;
    }

    private ProductServiceModel findProductById(Long id) {
        Product product = this.productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new NoSuchElementException();
        }
        return this.modelMapper.map(product, ProductServiceModel.class);
    }
    public ProductServiceModel mapProduct(ProductServiceModel productFound, ProductServiceModel productServiceModel) {
        productFound.setProductCondition(productServiceModel.getProductCondition());
        productFound.setName(productServiceModel.getName());
        productFound.setQuantity(productServiceModel.getQuantity());
        productFound.setCategory(productServiceModel.getCategory());
        productFound.setDescription(productServiceModel.getDescription());
        productFound.setPrice(productServiceModel.getPrice());
        return productFound;
    }
}
