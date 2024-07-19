package com.bbraun.pedidos.cotizacion.models.dto;

import com.bbraun.pedidos.cotizacion.models.Producto;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleDtoPDF {
    private String producto;
    private Float preciounitario;
    private Integer cantidad;
    private Float subtotal;

}
