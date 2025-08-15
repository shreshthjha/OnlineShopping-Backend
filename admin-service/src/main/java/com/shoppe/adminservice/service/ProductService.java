package com.shoppe.adminservice.service;

import java.util.List;
import com.shoppe.adminservice.model.Product;

public interface ProductService 
{
    Product addProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product approveProduct(Long id);
}
