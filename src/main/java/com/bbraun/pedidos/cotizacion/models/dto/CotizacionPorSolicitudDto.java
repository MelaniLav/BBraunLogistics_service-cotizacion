package com.bbraun.pedidos.cotizacion.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CotizacionPorSolicitudDto {
    private String idsolicitudcompra;
    private String idproveedor;
    private String nombreProveedor;
    private Date fechaEntregaCotizacion;
    private Float monto;
    private String estado;
    private Integer rate;
}
