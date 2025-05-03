package com.programacion.MarioOrtizHidalgo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Product {
    
    @Id @GeneratedValue
    private Long id;

    private String name;

    @Schema(description = "Cantidad del producto, puede ser una descripción como '1 docena', '500g', etc.")
    private String cantidad;

    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.PENDING;

}
