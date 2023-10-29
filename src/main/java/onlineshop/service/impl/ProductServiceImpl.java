package onlineshop.service.impl;

import lombok.RequiredArgsConstructor;
import onlineshop.model.entity.Product;
import onlineshop.model.repository.ProductRepository;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductServiceModel addProduct(ProductServiceModel productServiceModel) {
        Product product = new Product();
        Product savedProduct = this.productRepository.save(product);
        return modelMapper.map(savedProduct, ProductServiceModel.class);
    }

    @Override
    public ProductServiceModel editProduct(ProductServiceModel productServiceModel) {
        Product productFound = this.findProductById(productServiceModel.getId());
        Product editProduct = map(productFound,productServiceModel);
        return modelMapper.map(editProduct, ProductServiceModel.class);
    }
    @Override
    public boolean deleteProductById(long productId) {
        Product product = this.findProductById(productId);
        this.productRepository.delete(product);
        return true;
    }

    @Override
    public ProductServiceModel getProductById(long productId) {
        Product product = this.productRepository.findById(productId).orElse(null);
        return modelMapper.map(product, ProductServiceModel.class);
    }

    private Product findProductById(long id) {
        Product product = this.productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new NoSuchElementException();
        }
        return product;
    }
    private Product map(Product productFound, ProductServiceModel productServiceModel) {
        productFound.setId(productServiceModel.getId());
        productFound.setProductCondition(productServiceModel.getProductCondition());
        productFound.setName(productServiceModel.getName());
        productFound.setQuantity(productServiceModel.getQuantity());
        productFound.setCategory(productServiceModel.getCategory());
        productFound.setDescription(productServiceModel.getDescription());
        return productFound;
    }
}
