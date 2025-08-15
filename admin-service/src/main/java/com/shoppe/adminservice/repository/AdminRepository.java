package com.shoppe.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shoppe.adminservice.model.Product;

public interface AdminRepository extends JpaRepository<Product, Long> {
    // add custom queries if needed later
}
