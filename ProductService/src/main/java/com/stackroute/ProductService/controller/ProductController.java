package com.stackroute.ProductService.controller;


import com.stackroute.ProductService.exception.ProductAlreadyExistsException;
import com.stackroute.ProductService.exception.ProductDoesNotExistsException;
import com.stackroute.ProductService.model.Product;
import com.stackroute.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;
    private ResponseEntity responseEntity;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> followProduct(@PathVariable("userId") String userId,@RequestBody Product product) {
        try {
            Product followProduct = this.productService.followProduct(product,userId);
            responseEntity = new ResponseEntity<>(followProduct, HttpStatus.CREATED);
        } catch (ProductAlreadyExistsException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/{productId}/{userId}")
    public ResponseEntity<?> unfollowProduct(@PathVariable("productId") String productId, @PathVariable("userId") String userId) {
        try {
            Boolean unfollowProduct = this.productService.unfollowProduct(productId, userId);
            responseEntity = new ResponseEntity<>(unfollowProduct, HttpStatus.OK);
        } catch (ProductDoesNotExistsException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllProductForUser(@PathVariable("userId") String userId) {
        try {
            List<Product> productList = this.productService.getAllProductByUserId(userId);
            responseEntity = new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (ProductDoesNotExistsException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Internal Server Error, Try again in some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
