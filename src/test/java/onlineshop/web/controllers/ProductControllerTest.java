package onlineshop.web.controllers;

import onlineshop.model.binding.ProductAddBindingModel;
import onlineshop.model.service.ProductServiceModel;
import onlineshop.service.ProductService;
import onlineshop.model.view.ProductViewModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void getProductsReturnsCorrectModelAndView() throws Exception {
        ProductAddBindingModel addProductObject = new ProductAddBindingModel();

        ProductServiceModel productOne = new ProductServiceModel();
        productOne.setName("Product One");
        ProductServiceModel productTwo = new ProductServiceModel();
        productTwo.setName("Product Two");

        List<ProductServiceModel> allProducts = Arrays.asList(productOne, productTwo);

        Mockito.when(productService.getAll())
                .thenReturn(allProducts);

        List<ProductViewModel> products = allProducts.stream()
                .map(e -> modelMapper.map(e, ProductViewModel.class)).collect(Collectors.toList());

        MvcResult mvcResult = mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("products"))
                .andExpect(view().name("products"))
                .andReturn();

        ModelAndView mv = mvcResult.getModelAndView();

        assertThat(mv).isNotNull();
        assertThat(mv.getViewName()).isEqualTo("products");
        assertThat(mv.getModel().get("products")).asList().containsExactlyElementsOf(products);
        assertThat(mv.getModel().get("addProductObject")).isInstanceOf(ProductAddBindingModel.class);
    }
}