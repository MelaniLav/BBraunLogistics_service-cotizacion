package com.bbraun.pedidos.cotizacion.models.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolicitudCotizacionDTO {
    private String idsolicitudcotizacion;
    private String idsolicitudcompra;
    private Date fechacreacion;
    private List<DetalleCotizacionCompraDTO> detalles;
}
