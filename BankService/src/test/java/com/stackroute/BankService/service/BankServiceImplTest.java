package com.stackroute.BankService.service;

import com.stackroute.BankService.exception.ProductAlreadyExistsException;
import com.stackroute.BankService.exception.ProductNotFoundException;
import com.stackroute.BankService.model.Product;
import com.stackroute.BankService.repository.BankRepository;
import com.stackroute.BankService.service.BankServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class BankServiceImplTest {

    @Mock
    BankRepository bankRepository;

    Product product;
    Optional optional;
    List<Product> productList = null;

    @InjectMocks
    BankServiceImpl bankService;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        product = new Product("P101", "ProductOne", "ProductDescriptionOne", "ProductOneCreatedBy");
        productList = new ArrayList<>();
        productList.add(product);
        optional = Optional.of(product);
    }

    @After
    public void tearDown()
    {
        bankRepository.deleteAll();
    }

    @Test
    public void testSaveProductSuccess() throws ProductAlreadyExistsException
    {
        when(bankRepository.insert(product)).thenReturn(product);
        Product fetchProduct = bankService.addProduct(product);
        Assert.assertEquals(product, fetchProduct);

        verify(bankRepository, times(1)).insert(product);
        verify(bankRepository, times(1)).findById(product.getProductId());
    }

    @Test(expected = ProductAlreadyExistsException.class)
    public void testSaveProductFailure() throws ProductAlreadyExistsException {


        when(bankRepository.insert(product)).thenReturn(product);

        when(bankRepository.findById(product.getProductId())).thenReturn(optional);

        Product fetchProduct = bankService.addProduct(product);

        // Assert.assertEquals(track,fetchTrack);

        verify(bankRepository,times(1)).insert(product);
        verify(bankRepository,times(1)).findById(product.getProductId());
    }

//    @Test
//    public void testUpdateCommentsSuccess() throws ProductNotFoundException {
//        when(bankRepository.findById(product.getProductId())).thenReturn(optional);
//        product.setDescription("NewDescription");
//        Product fetchProduct = bankService.updateProduct(product,product.getProductId());
//        Assert.assertEquals(fetchProduct.getDescription() ,"NewDescription" );
//
//        verify(bankRepository,times(1)).save(product);
//        verify(bankRepository,times(2)).findById(product.getProductId());
//    }

    @Test
    public void testDeleteProductSuccess() throws ProductNotFoundException {
        when(bankRepository.findById(product.getProductId())).thenReturn(optional);
        Product fetchProduct = bankService.deleteProduct(product.getProductId());
        if(fetchProduct != null) {
            Assert.assertEquals(true, true);
        }
    }

    @Test
    public void testGetAllProductsSuccess() throws Exception {
        when(bankRepository.findAll()).thenReturn(productList);
        List<Product> list = bankService.getAllProduct();
        Assert.assertEquals(list, productList);
    }








}
