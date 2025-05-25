package com.programacion.MarioOrtizHidalgo.controller;

import com.programacion.MarioOrtizHidalgo.model.Product;
import com.programacion.MarioOrtizHidalgo.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Productos", description = "Gestión básica de productos.")
@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4321")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(
            summary = "Obtener todos los productos de la lista.",
            description = "Método para obtener todos los productos actualmente guardados en la base de datos."
    )
    @ApiResponse(
            description = "Información sobre todos los productos",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Product.class),
                    examples = {
                            @ExampleObject(""" 
                                    [
                                        {
                                            "name": "Leche",
                                            "cantidad": "2 litros",
                                            "estado": "PENDING"
                                        },
                                        {
                                            "name": "Pan",
                                            "cantidad": "1 docena",
                                            "estado": "PENDING"
                                        }
                                    ]
                                    """)
                    }
            )
    )
    /**
     * Maneja las solicitudes HTTP GET para obtener todos los productos.
     *
     * @return respuesta HTTP:
     *         - 200 OK con la lista de productos si existen productos.
     *         - 204 No Content si no hay productos disponibles.
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = productService.getAll();

        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(products);
    }

    @Operation(
            summary = "Obtener un producto por su ID",
            description = "Método para obtener un producto específico utilizando su ID."
    )
    @ApiResponse(
            description = "Producto encontrado",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Product.class)
            )
    )
    @ApiResponse(
            description = "Producto no encontrado",
            responseCode = "404",
            content = @Content(
                    mediaType = "application/json"
            )
    )
    /**
     * Maneja las solicitudes HTTP GET para obtener un producto por su ID.
     *
     * @param id el ID del producto a obtener.
     * @return el producto correspondiente al ID especificado.
     *
     */
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @Operation(
            summary = "Crear un nuevo producto",
            description = "Método para crear un nuevo producto en la lista. El campo 'cantidad' es un String que puede incluir valores como '1 docena', '500g', etc."
    )
    @ApiResponse(
            description = "Producto creado exitosamente",
            responseCode = "201",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Product.class),
                    examples = {
                            @ExampleObject("""
                                    {
                                        "id": 1,
                                        "name": "Leche",
                                        "cantidad": "2 litros",
                                    }
                                    """)
                    }
            )
    )
    /**
     * Maneja las solicitudes HTTP POST para crear un nuevo producto.
     *
     * @param product el producto que se desea crear.
     * @return el producto creado.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @Operation(
            summary = "Actualizar un producto",
            description = "Método para actualizar un producto existente en la lista."
    )
    @ApiResponse(
            description = "Producto actualizado",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Product.class)
            )
    )
    @ApiResponse(
            description = "Producto no encontrado",
            responseCode = "404",
            content = @Content(
                    mediaType = "application/json"
            )
    )
    /**
     * Maneja las solicitudes HTTP PUT para actualizar un producto existente.
     *
     * @param id el ID del producto a actualizar.
     * @param product el producto con los nuevos datos.
     * @return el producto actualizado.
     * 
     */
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @Operation(
            summary = "Eliminar un producto",
            description = "Método para eliminar un producto de la lista."
    )
    @ApiResponse(
            description = "Producto eliminado",
            responseCode = "204",
            content = @Content(
                    mediaType = "application/json"
            )
    )
    @ApiResponse(
            description = "Producto no encontrado",
            responseCode = "404",
            content = @Content(
                    mediaType = "application/json"
            )
    )
    /**
     * Maneja las solicitudes HTTP DELETE para eliminar un producto por su ID.
     *
     * @param id el ID del producto a eliminar.
     * 
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }

    @Operation(
            summary = "Cambiar el estado de un producto",
            description = "Método para cambiar el estado de un producto a 'PENDING' o 'PURCHASED'."
    )
    @ApiResponse(
            description = "Estado del producto cambiado",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Product.class)
            )
    )
    @ApiResponse(
            description = "Producto no encontrado",
            responseCode = "404",
            content = @Content(
                    mediaType = "application/json"
            )
    )
    /**
     * Maneja las solicitudes HTTP PATCH para cambiar el estado de un producto
     * (por ejemplo, de 'PENDING' a 'PURCHASED').
     *
     * @param id el ID del producto cuyo estado se desea cambiar.
     * @return el producto con el estado actualizado.
     * 
     */
    @PatchMapping("/{id}/change-status")
    public Product markAsCompleted(@PathVariable Long id) {
        return productService.changeStatus(id);
    }

}