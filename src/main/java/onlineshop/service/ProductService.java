package onlineshop.service;

import onlineshop.model.service.ProductServiceModel;

public interface ProductService {

    ProductServiceModel addProduct(ProductServiceModel product);
    ProductServiceModel editProduct(ProductServiceModel product);
    boolean deleteProductById(Long productId);
    ProductServiceModel getProductById(Long productId);

}
