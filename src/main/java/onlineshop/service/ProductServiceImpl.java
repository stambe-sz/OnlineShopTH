package onlineshop.service;

import onlineshop.model.service.ProductServiceModel;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public ProductServiceModel addProduct(ProductServiceModel product) {
        return null;
    }

    @Override
    public ProductServiceModel editProduct(ProductServiceModel product) {
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
}
