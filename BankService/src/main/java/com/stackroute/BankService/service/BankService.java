package com.stackroute.BankService.service;

import com.stackroute.BankService.exception.ProductAlreadyExistsException;
import com.stackroute.BankService.exception.ProductNotFoundException;
import com.stackroute.BankService.model.Product;

import java.util.List;


public interface BankService {


    Product addProduct(Product product) throws ProductAlreadyExistsException;

    Product deleteProduct(String productId) throws ProductNotFoundException;

    Product updateProduct(Product product, String productId) throws ProductNotFoundException;

    Product getProductByProductId(String productId) throws ProductNotFoundException;

    List<Product> getAllProduct();

}
