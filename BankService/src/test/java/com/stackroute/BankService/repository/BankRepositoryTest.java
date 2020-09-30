package com.stackroute.BankService.repository;

import com.stackroute.BankService.model.Product;
import com.stackroute.BankService.repository.BankRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataMongoTest
public class BankRepositoryTest {

    @Autowired
    BankRepository bankRepository;

    Product product;

    @Before
    public void setUp()
    {
        product = new Product("P101", "ProductOne", "ProductDescriptionOne", "ProductOneCreatedBy");
    }

    @After
    public void tearDown()
    {
        product = null;
        bankRepository.deleteAll();
    }

    @Test
    public void testSaveProduct()
    {
        bankRepository.save(product);
        Product fetchProduct = bankRepository.findById(product.getProductId()).get();
        Assert.assertEquals( product.getProductId(),fetchProduct.getProductId());
    }

//    @Test
//    public void testGetAllProducts()
//    {
//        bankRepository.insert(product);
//        Product productTwo = new Product("P102", "ProductTwo", "ProductDescriptionTwo", "ProductTwoCreatedBy");
//        bankRepository.insert(product);
//
//        List<Product> productList = bankRepository.findAll();
//        Assert.assertEquals(2, productList.size());
//    }

    @Test
    public void testDeleteProduct()
    {
        bankRepository.insert(product);
        Product fetchProduct = bankRepository.findById(product.getProductId()).get();
        bankRepository.delete(fetchProduct);
        Assert.assertEquals(Optional.empty(), bankRepository.findById(product.getProductId()));
    }

    @Test
    public void testUpdateProduct()
    {
        bankRepository.insert(product);
        Product fetchProduct = bankRepository.findById(product.getProductId()).get();
        fetchProduct.setDescription("NewDescription");
        bankRepository.save(fetchProduct);
        Product fetchProductObj = bankRepository.findById(product.getProductId()).get();
        Assert.assertEquals("NewDescription",fetchProductObj.getDescription());
    }



}

