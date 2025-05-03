package com.programacion.MarioOrtizHidalgo.repository;

import com.programacion.MarioOrtizHidalgo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
