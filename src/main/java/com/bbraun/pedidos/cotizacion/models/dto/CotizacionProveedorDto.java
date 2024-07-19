package com.bbraun.pedidos.cotizacion.models.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CotizacionProveedorDto {
    private String codigo_solicitud_cotizacion;
    private String codigo_proveedor;
    private Float monto;
    private String fecha_entrega;
}
