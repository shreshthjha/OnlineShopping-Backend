package com.shoppe.adminservice.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.shoppe.adminservice.model.Product;
import com.shoppe.adminservice.exception.ResourceNotFoundException;
import com.shoppe.adminservice.repository.AdminRepository;

@Service
public class ProductServiceImpl implements ProductService 
{

    private final AdminRepository productRepository;

    public ProductServiceImpl(AdminRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        product.setApproved(false); // default not approved
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return productRepository.findById(id)
                .map(existing -> {
                    existing.setName(product.getName());
                    existing.setDescription(product.getDescription());
                    existing.setPrice(product.getPrice());
                    existing.setAvailable(product.isAvailable());
                    // don't change approved flag here unless explicitly approving
                    return productRepository.save(existing);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    @Override
    public Product approveProduct(Long id) {
        return productRepository.findById(id)
                .map(p -> {
                    p.setApproved(true);
                    return productRepository.save(p);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }
}
