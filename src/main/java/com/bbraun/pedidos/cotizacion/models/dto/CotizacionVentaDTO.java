package com.bbraun.pedidos.cotizacion.models.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CotizacionVentaDTO {
    private String idcotizacion;
    private String idempleado;
    private String estado;
    private String nombrecliente;
    private Float montoproducto;
    private Date fechaemision;
    private String email;
    private Float montoimpuesto;
    private Float montototal;
    private String departamento;
    private List<DetalleCotizacionVentaDTO> detalles;
    private String dni;
}
