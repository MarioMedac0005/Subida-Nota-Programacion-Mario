package com.programacion.MarioOrtizHidalgo.service;

import com.programacion.MarioOrtizHidalgo.exception.ProductNotFoundException;
import com.programacion.MarioOrtizHidalgo.model.Product;
import com.programacion.MarioOrtizHidalgo.model.ProductStatus;
import com.programacion.MarioOrtizHidalgo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(updatedProduct.getName());
        product.setCantidad(updatedProduct.getCantidad());
        product.setStatus(updatedProduct.getStatus());

        return productRepository.save(product);
    }

    public Product changeStatus(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado."));
        if (product.getStatus() == ProductStatus.PENDING) {
            product.setStatus(ProductStatus.PURCHASED);
        } else {
            product.setStatus(ProductStatus.PENDING);
        }

        return productRepository.save(product);
    }

}
