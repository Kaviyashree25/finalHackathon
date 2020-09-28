package com.stackroute.ProductService.service;

import com.stackroute.ProductService.exception.ProductAlreadyExistsException;
import com.stackroute.ProductService.exception.ProductDoesNotExistsException;
import com.stackroute.ProductService.model.Product;
import com.stackroute.ProductService.model.ProductUser;
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
    public Product followProduct(Product product,String userId) throws ProductAlreadyExistsException {
            Product createdProduct;
            Optional<ProductUser> userOptional = this.productRepository.findById(userId);
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
                        ProductUser user = new ProductUser();
                        user.setUserId(userId);
                        user.setProducts(list);
                        this.productRepository.save(user);
                        createdProduct = product;
                    }
                return createdProduct;
    }

    @Override
        public Product unfollowProduct(String productId, String userId) throws ProductDoesNotExistsException{
        Optional<ProductUser> userOptional = this.productRepository.findById(userId);
        if(userOptional.isPresent()) {
            ProductUser user = userOptional.get();
            if (user.getProducts() != null) {
                Optional<Product> unfollowedProduct = user.getProducts().stream()
                        .filter(product -> product.getProductId().equals(productId))
                        .findFirst();
                if (unfollowedProduct.isPresent()) {
                    user.getProducts().remove(unfollowedProduct.get());
                    this.productRepository.deleteById(userId);
                    this.productRepository.save(user);
                    return unfollowedProduct.get();
                } else {
                    throw new ProductDoesNotExistsException("Product not found");
                }
            } else {
                //No circle present for the user, hence nothing to be deleted
                throw new ProductDoesNotExistsException("Product not found");
            }
        }
        throw new ProductDoesNotExistsException("No user Found");
    }

    @Override
    public List<Product> getAllProductByUserId(String userId) throws ProductDoesNotExistsException {
        Optional<ProductUser> userOptional = this.productRepository.findById(userId);
        if(userOptional.isPresent()){
            return userOptional.get().getProducts();
        }
        throw new ProductDoesNotExistsException("No user Found");
    }

    @Override
    public List<ProductUser> getAllUserByProductId(String productId){
        return this.productRepository.findUserByProduct(productId);
    }
}
