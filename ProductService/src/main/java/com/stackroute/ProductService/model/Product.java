package com.stackroute.ProductService.model;

import java.util.List;

public class Product {
    private String productId;
    private String productName;
    private String description;
    private String productImage;


    public Product() {
    }

    public Product(String productId, String productName, String description, String productImage, List<ProductUser> users) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.productImage = productImage;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }


    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", productImage='" + productImage + '\'' +
                '}';
    }
}
