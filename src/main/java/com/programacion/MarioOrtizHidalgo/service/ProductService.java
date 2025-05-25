package com.programacion.MarioOrtizHidalgo.service;

import com.programacion.MarioOrtizHidalgo.exception.ProductNotFoundException;
import com.programacion.MarioOrtizHidalgo.model.Product;
import com.programacion.MarioOrtizHidalgo.model.ProductStatus;
import com.programacion.MarioOrtizHidalgo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para gestionar operaciones relacionadas con productos.
 * Proporciona métodos para acceder, crear, actualizar, eliminar y cambiar el estado de productos.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Constructor que inyecta el repositorio de productos.
     *
     * @param productRepository el repositorio JPA de productos.
     */
    public ProductService (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Obtiene todos los productos existentes.
     *
     * @return una lista con todos los productos.
     */
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    /**
     * Busca un producto por su ID.
     *
     * @param id el ID del producto a buscar.
     * @return el producto correspondiente al ID.
     * @throws ProductNotFoundException si no se encuentra el producto.
     */
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    /**
     * Guarda un nuevo producto en la base de datos.
     *
     * @param product el producto a guardar.
     * @return el producto guardado.
     */
    public Product save(Product product) {
        return productRepository.save(product);
    }

    
    /**
     * Elimina un producto por su ID.
     *
     * @param id el ID del producto a eliminar.
     */
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * Actualiza los datos de un producto existente.
     *
     * @param id el ID del producto a actualizar.
     * @param updatedProduct los nuevos datos del producto.
     * @return el producto actualizado.
     * @throws ProductNotFoundException si no se encuentra el producto a actualizar.
     */
    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        product.setNombre(updatedProduct.getNombre());
        product.setCantidad(updatedProduct.getCantidad());
        product.setEstado(updatedProduct.getEstado());

        return productRepository.save(product);
    }

    /**
     * Cambia el estado de un producto entre PENDIENTE y COMPRADO.
     *
     * @param id el ID del producto cuyo estado se desea cambiar.
     * @return el producto con el estado actualizado.
     * @throws RuntimeException si no se encuentra el producto.
     */
    public Product changeStatus(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado."));
        if (product.getEstado() == ProductStatus.PENDIENTE) {
            product.setEstado(ProductStatus.COMPRADO);
        } else {
            product.setEstado(ProductStatus.PENDIENTE);
        }

        return productRepository.save(product);
    }

}
