package com.bbraun.pedidos.cotizacion.models.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleCotizacionVentaDTO {
    private String idcotizacion;
    private String producto;
    private String concentracion;
    private Integer cantidad;
    private Float total;
}
