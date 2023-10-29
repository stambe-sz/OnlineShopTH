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
        return null;
    }

    @Override
    public boolean deleteProductById(long productId) {
        return false;
    }

    @Override
    public ProductServiceModel getProductById(long productId) {
        return null;
    }

    private Product findProductById(long id) {

        Product product = this.productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new NoSuchElementException();
        }
        return product;
    }
}
