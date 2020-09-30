package com.stackroute.BankService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.BankService.controller.BankController;
import com.stackroute.BankService.exception.ProductNotFoundException;
import com.stackroute.BankService.model.Product;
import com.stackroute.BankService.service.BankService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(BankController.class)
public class BankControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankService bankService;

    Product product;
    List<Product> productList;

    @Before
    public void setUp()
    {
        productList = new ArrayList<>();

        product = new Product("P101", "ProductOne", "ProductDescriptionOne", "ProductOneCreatedBy");
        productList.add(product);

        product = new Product("P102", "ProductTwo", "ProductDescriptionTwo", "ProductTwoCreatedBy");
        productList.add(product);
    }

    @Test
    public void testSaveProductSuccess() throws Exception {

        when(bankService.addProduct(any())).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/bank/products")
                .contentType(MediaType.APPLICATION_JSON).
                        content(jsonToString(product)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }

//    @Test
//    public void testUpdateProductSuccess() throws Exception {
//        product.setDescription("NewDescription");
//        when(bankService.updateProduct(eq(product),any())).thenReturn(product);
//        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/bank/products/P101")
//                .contentType(MediaType.APPLICATION_JSON).
//                        content(jsonToString(product)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }

    @Test
    public void testDeleteProductSuccess() throws Exception {
        when(bankService.deleteProduct("P101")).thenReturn(product);
        mockMvc.perform(delete("/api/v1/bank/products/P101")
                .contentType(MediaType.APPLICATION_JSON).
                        content(jsonToString(product)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
//
//    @Test
//    public void testDeleteProductFailure() throws Exception {
//        when(bankService.deleteProduct("P101")).thenThrow(ProductNotFoundException.class);
//        mockMvc.perform(delete("/api/v1/bank/products/P101")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andDo(MockMvcResultHandlers.print());
//    }


    private static String jsonToString(final Object ob) throws JsonProcessingException {
        String result;

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            result = jsonContent;
        } catch(JsonProcessingException e) {
            result = "JSON processing error";
        }

        return result;
    }









}
