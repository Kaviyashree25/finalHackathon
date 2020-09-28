package com.stackroute.BankService.service;

import com.stackroute.BankService.exception.ProductAlreadyExistsException;
import com.stackroute.BankService.exception.ProductNotFoundException;
import com.stackroute.BankService.model.Product;
import com.stackroute.BankService.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {

    private static final String NOT_FOUND_MESSAGE = "Product Not Found";
    private static final String ALREADY_EXISTS = "Product Already Exists";
    private final BankRepository bankRepository;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public Product addProduct(Product product) throws ProductAlreadyExistsException {
        Optional<Product> optionalProduct = this.bankRepository.findById(product.getProductId());
        if (optionalProduct.isPresent()) {
            throw new ProductAlreadyExistsException(ALREADY_EXISTS);
        }
        return this.bankRepository.insert(product);
    }

    @Override
    public Product deleteProduct(String productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct = this.bankRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException(NOT_FOUND_MESSAGE);
        }
        this.bankRepository.deleteById(productId);
        return optionalProduct.get();
    }

    @Override
    public Product updateProduct(Product product, String productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct = this.bankRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException(NOT_FOUND_MESSAGE);
        }
        this.bankRepository.deleteById(productId);
        return this.bankRepository.insert(product);
    }

    @Override
    public Product getProductByProductId(String productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct = this.bankRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException(NOT_FOUND_MESSAGE);
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProduct() {
        return this.bankRepository.findAll();
    }
}
