package com.programacion.MarioOrtizHidalgo.repository;

import com.programacion.MarioOrtizHidalgo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad {@link Product}.
 * Proporciona operaciones CRUD y de consulta automática sobre la base de datos.
 *
 * Hereda métodos de {@link JpaRepository}, como:
 * - findAll()
 * - findById(Long id)
 * - save(Product entity)
 * - deleteById(Long id)
 * - etc.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
