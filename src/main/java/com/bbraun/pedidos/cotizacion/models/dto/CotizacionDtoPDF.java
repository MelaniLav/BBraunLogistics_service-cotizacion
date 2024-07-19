package com.bbraun.pedidos.cotizacion.models.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CotizacionDtoPDF {
    private String nombrecliente;
    private Float montototal;
    private Date fecha_emision;
    private String dnicliente;
    private Float impuestos;
    private Float total;
    private String departamento;
    private List<DetalleDtoPDF> detalles;
}
