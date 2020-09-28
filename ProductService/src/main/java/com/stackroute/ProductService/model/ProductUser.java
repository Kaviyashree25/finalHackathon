package com.stackroute.ProductService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
public class ProductUser {
    @Id
    private String userId;
    private List<Product> products;

    public ProductUser() {
    }

    public ProductUser(String userId, List<Product> products) {
        this.userId = userId;
        this.products = products;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", products=" + products +
                '}';
    }
}
