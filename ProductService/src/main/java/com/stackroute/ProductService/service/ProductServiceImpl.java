package com.stackroute.ProductService.service;

import com.stackroute.ProductService.exception.ProductAlreadyExistsException;
import com.stackroute.ProductService.exception.ProductDoesNotExistsException;
import com.stackroute.ProductService.model.Product;
import com.stackroute.ProductService.model.User;
import com.stackroute.ProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product followProduct(Product product,String userId) throws ProductAlreadyExistsException, ProductDoesNotExistsException {
            Product createdProduct;
            Optional<User> userOptional = this.productRepository.findById(userId);
            if (userOptional.isPresent()) {
                if (userOptional.get().getProducts() != null) {
                        Optional<Product> productOptional = userOptional.get().getProducts().stream()
                                .filter(product1 -> product1.getProductId().equals(product.getProductId()))
                                .findFirst();
                        if (productOptional.isPresent()) {
                            throw new ProductAlreadyExistsException("Product Already Followed");
                        } else {
                            userOptional.get().getProducts().add(product);
                            this.productRepository.deleteById(userId);
                            this.productRepository.save(userOptional.get());
                            createdProduct = product;
                        }
                    }else {
                    List<Product> list = new ArrayList<>();
                    list.add(product);
                    userOptional.get().setProducts(list);
                    this.productRepository.deleteById(userId);
                    this.productRepository.save(userOptional.get());
                    createdProduct = product;
                }
            }else {
                        List<Product> list = new ArrayList<>();
                        list.add(product);
                        User user = new User();
                        user.setUserId(userId);
                        product.getUsers().add(user);
                        user.setProducts(list);
                        this.productRepository.save(user);
                        createdProduct = product;
                    }
                return createdProduct;
    }

    @Override
        public boolean unfollowProduct(String productId, String userId) throws ProductDoesNotExistsException{
        Optional<User> userOptional = this.productRepository.findById(userId);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            boolean status = false;
            if (user.getProducts() != null) {
                Optional<Product> unfollowedProduct = user.getProducts().stream()
                        .filter(product -> product.getProductId().equals(productId))
                        .findFirst();
                if (unfollowedProduct.isPresent()) {
                    user.getProducts().remove(unfollowedProduct.get());
                    this.productRepository.delete(user);
                    this.productRepository.save(user);
                    status = true;

                } else {
                    throw new ProductDoesNotExistsException("Product not found");
                }
            } else {
                //No circle present for the user, hence nothing to be deleted
                throw new ProductDoesNotExistsException("Product not found");
            }
            return status;
        }
        throw new ProductDoesNotExistsException("No user Found");
    }

    @Override
    public List<Product> getAllProductByUserId(String userId) throws ProductDoesNotExistsException {
        Optional<User> userOptional = this.productRepository.findById(userId);
        if(userOptional.isPresent()){
            return userOptional.get().getProducts();
        }
        throw new ProductDoesNotExistsException("No user Found");
    }
}
