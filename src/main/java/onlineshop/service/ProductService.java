package onlineshop.service;

import onlineshop.model.service.ProductServiceModel;

import java.util.List;

public interface ProductService {

    ProductServiceModel addProduct(ProductServiceModel product);
    ProductServiceModel editProduct(ProductServiceModel product);
    boolean deleteProductById(Long productId);
    ProductServiceModel getProductById(Long productId);

    List<ProductServiceModel> getAll();

}
