package com.shoppe.adminservice.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.shoppe.adminservice.model.Product;
import com.shoppe.adminservice.service.ProductService;
import com.shoppe.adminservice.feign.UserServiceClient;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController 
{

    private final ProductService productService;
    private final UserServiceClient userServiceClient; // Feign

    public AdminController(ProductService productService, UserServiceClient userServiceClient) {
        this.productService = productService;
        this.userServiceClient = userServiceClient;
    }

    // Get all products (list for admin)
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Add product
    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        Product saved = productService.addProduct(product);
        return ResponseEntity.ok(saved);
    }

    // Update product
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        Product updated = productService.updateProduct(id, product);
        return ResponseEntity.ok(updated);
    }

    // Delete product
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    // Approve product
    @PutMapping("/approve-product/{productId}")
    public ResponseEntity<String> approveProduct(@PathVariable Long productId) {
        productService.approveProduct(productId);
        return ResponseEntity.ok("Product approved successfully");
    }

    // Block user - uses Feign to call User Service (placeholder). If user-service not available, handle gracefully.
    @PutMapping("/block-user/{userId}")
    public ResponseEntity<String> blockUser(@PathVariable Long userId) {
        // call user service via feign client - method signature is expected to exist in user service
        try {
            userServiceClient.blockUser(userId);
            return ResponseEntity.ok("User block request sent");
        } catch (Exception ex) {
            // If user-service not available, still return success message or an informative message
            return ResponseEntity.ok("Attempted to block user (user-service may be down).");
        }
    }
}
