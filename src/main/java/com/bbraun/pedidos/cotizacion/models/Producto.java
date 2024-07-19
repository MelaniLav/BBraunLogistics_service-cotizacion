package com.bbraun.pedidos.cotizacion.models;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto {
    private String code;
    private String category;
    private String type;
    private String name;
    private Float price;
    private String concentracion;
    private String presentation;
    private String description;
}
