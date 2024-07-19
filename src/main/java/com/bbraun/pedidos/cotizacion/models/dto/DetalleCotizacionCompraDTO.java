package com.bbraun.pedidos.cotizacion.models.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleCotizacionCompraDTO {
    private String idsolicitudcotizacion;
    private String idproveedor;
}
