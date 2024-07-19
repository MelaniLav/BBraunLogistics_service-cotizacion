package com.bbraun.pedidos.cotizacion.models.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CotizacionCompraDto {

    private String codigo_solicitud_cotizacion;
    private String codigo_proveedor;
    private String estado;
    private Float monto;
    private String fecha_entrega;
}
