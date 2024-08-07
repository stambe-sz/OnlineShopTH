package onlineshop.service.impl;

import onlineshop.model.entity.Category;
import onlineshop.model.entity.Product;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.repository.ProductRepository;
import onlineshop.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;
    
    @Mock
    private ProductRepository productRepository;
    
    @Mock
    private CategoryService categoryService;

    @Mock
    private ModelMapper modelMapper;



    @Test
    public void addProduct_ExistingProductName_IllegalArgumentExceptionThrown() {
        // arrange
        Product existingProduct = new Product();
        existingProduct.setName("Existing Product");

        ProductServiceModel newProduct = new ProductServiceModel();
        newProduct.setName("Existing Product");
        
        when(productRepository.findProductByName(anyString())).thenReturn(Optional.of(existingProduct));
        
        // act and assert
        assertThrows(IllegalArgumentException.class, () -> productService.addProduct(newProduct));
    }

   @Test
   public void deleteProductById_ProductDoesNotExist_NoSuchElementExceptionThrown() {
       // arrange
       when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

       // act and assert
       assertThrows(NoSuchElementException.class, () -> productService.deleteProductById(1L));
       assertThrows(NoSuchElementException.class, () -> productService.deleteProductById(1L));
   }

   @Test
   public void getProductById_ProductDoesNotExist_NullReturned() {
       // arrange
       when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

       // act
       ProductServiceModel result = productService.getProductById(1L);

       // assert
       assertNull(result, "Null should be returned for a non-existing product");
   }
    @Test
    public void getAll_NoProducts_NoProductReturned() {
        // arrange
        when(productRepository.findAll()).thenReturn(List.of());

        // act
        List<ProductServiceModel> result = productService.getAll();

        // assert
        assertTrue(result.isEmpty(), "No products should be returned");
    }

    @Test
    public void getAll_SingleProduct_ProductListReturned() {
        // arrange
        Product product = new Product();
        product.setName("Test Product");

        when(productRepository.findAll()).thenReturn(List.of(product));
        when(modelMapper.map(product, ProductServiceModel.class)).thenReturn(new ProductServiceModel());

        // act
        List<ProductServiceModel> result = productService.getAll();

        // assert
        assertEquals(1, result.size(), "A list with a single product should be returned");
    }

    @Test
    public void getAll_MultipleProducts_ProductListReturned() {
        // arrange
        Product product1 = new Product();
        product1.setName("Test Product 1");

        Product product2 = new Product();
        product2.setName("Test Product 2");

        when(productRepository.findAll()).thenReturn(List.of(product1, product2));
        when(modelMapper.map(product1, ProductServiceModel.class)).thenReturn(new ProductServiceModel());
        when(modelMapper.map(product2, ProductServiceModel.class)).thenReturn(new ProductServiceModel());

        // act
        List<ProductServiceModel> result = productService.getAll();

        // assert
        assertEquals(2, result.size(), "A list with two products should be returned");
    }
}
