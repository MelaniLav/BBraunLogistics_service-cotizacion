package com.bbraun.pedidos.cotizacion.models;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proveedor {
    private String idproveedor;
    private Integer idCaracteristica;
    private String empresa;
    private String direccion;
    private String telefono;
    private String email;
    private String ruc;
    private Integer rate;
}
