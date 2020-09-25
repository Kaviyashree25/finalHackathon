package com.stackroute.BankService.controller;

import com.stackroute.BankService.exception.ProductAlreadyExistsException;
import com.stackroute.BankService.exception.ProductNotFoundException;
import com.stackroute.BankService.model.Product;
import com.stackroute.BankService.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/bank/products")
public class BankController {

    private BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    //List<User>
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUsersByProduct(@PathVariable("id") String productId) {
        ResponseEntity<?> responseEntity;
        try {
            Product savedProduct = this.bankService.getProductByProductId(productId);
            responseEntity = new ResponseEntity<>(savedProduct.getUsers(), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Some Internal Error Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //Product
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductByProductId(@PathVariable("id") String productId) {
        ResponseEntity<?> responseEntity;
        try {
            Product savedProduct = this.bankService.getProductByProductId(productId);
            responseEntity = new ResponseEntity<>(savedProduct, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    //List<Product>
    @GetMapping("")
    public ResponseEntity<?> getAllProduct() {
        return new ResponseEntity<>(this.bankService.getAllProduct(), HttpStatus.OK);
    }
    //Product
    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        ResponseEntity<?> responseEntity;
        try {
            Product savedProduct = this.bankService.addProduct(product);
            responseEntity = new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (ProductAlreadyExistsException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Some Internal Error Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    //Boolean
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") String productId) {
        ResponseEntity<?> responseEntity;
        try {
            Product savedProduct = this.bankService.getProductByProductId(productId);
            this.bankService.deleteProduct(productId);
            responseEntity = new ResponseEntity<>(savedProduct, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Some Internal Error Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //Product
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable("id") String productId) {
        ResponseEntity<?> responseEntity;
        try {
            Product savedProduct = this.bankService.updateProduct(product, productId);
            responseEntity = new ResponseEntity<>(savedProduct, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Some Internal Error Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


}
