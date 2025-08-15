package com.shoppe.productservice.exception;

//ProductNotFoundException.java
public class ProductNotFoundException extends RuntimeException {
 public ProductNotFoundException(String message) {
     super(message);
 }
}

