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
    public void addProduct_ValidInput_ProductCreated() {
        // arrange
        ProductServiceModel inputProduct = new ProductServiceModel();
        inputProduct.setName("Test Product");

        Category category = new Category();
        category.setCategoryName("Test Category");

        Product mappedProduct = new Product();
        mappedProduct.setName("Test Product");

        Product savedProduct = new Product();
        savedProduct.setName("Test Product");

        when(productRepository.findProductByName(anyString())).thenReturn(Optional.empty());
        when(modelMapper.map(inputProduct, Product.class)).thenReturn(mappedProduct);
        when(categoryService.findCategoryByName(anyString())).thenReturn(category);
        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);
        when(modelMapper.map(savedProduct, ProductServiceModel.class)).thenReturn(inputProduct);
        
        // act
        ProductServiceModel result = productService.addProduct(inputProduct);
        
        // assert
        assertEquals(inputProduct, result, "The created product should be returned");
    }

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
    public void editProduct_TargetProductExists_ProductEdited() {
        // arrange
        ProductServiceModel existingProduct = new ProductServiceModel();
        existingProduct.setId(1L);
        existingProduct.setName("Existing Product");

        ProductServiceModel updatedProduct = new ProductServiceModel();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Product");

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(modelMapper.map(existingProduct, Product.class)));
       when(productRepository.save(any(Product.class))).thenReturn(modelMapper.map(updatedProduct, Product.class));

        // act
        ProductServiceModel result = productService.editProduct(updatedProduct);

        // assert
        assertEquals(updatedProduct, result, "The existing product should be replaced with updated product without changing ID");
    }

    @Test
    public void editProduct_TargetProductDoesNotExist_NoSuchElementExceptionThrown() {
        // arrange
       ProductServiceModel nonExistingProduct = new ProductServiceModel();
       nonExistingProduct.setId(1L);
       nonExistingProduct.setName("Non Existing Product");

       when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

       // act and assert
       assertThrows(NoSuchElementException.class, () -> productService.editProduct(nonExistingProduct));
   }

   @Test
   public void deleteProductById_ProductExists_ProductDeleted () {
       // arrange
       ProductServiceModel existingProduct = new ProductServiceModel();
       existingProduct.setId(1L);
       existingProduct.setName("Existing Product");

       when(productRepository.findById(anyLong())).thenReturn(Optional.of(modelMapper.map(existingProduct, Product.class)));

       // act
       boolean result = productService.deleteProductById(1L);

       // assert
       assertTrue(result, "The product should be deleted successfully");
       verify(productRepository, times(1)).delete(any(Product.class));
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
   public void getProductById_ProductExists_ProductReturned() {
       // arrange
       ProductServiceModel existingProduct = new ProductServiceModel();
       existingProduct.setId(1L);
       existingProduct.setName("Existing Product");

       when(productRepository.findById(anyLong())).thenReturn(Optional.of(modelMapper.map(existingProduct, Product.class)));
       when(modelMapper.map(any(Product.class), eq(ProductServiceModel.class))).thenReturn(existingProduct);

       // act
       ProductServiceModel result = productService.getProductById(1L);

       // assert
       assertEquals(existingProduct, result, "The product should be returned successfully");
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
