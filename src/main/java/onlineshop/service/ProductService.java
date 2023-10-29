package onlineshop.service;

import onlineshop.model.service.ProductServiceModel;

public interface ProductService {

    ProductServiceModel addProduct(ProductServiceModel product);
    ProductServiceModel editProduct(ProductServiceModel product);
    boolean deleteProductById(long productId);
    ProductServiceModel getProductById(long productId);

}
