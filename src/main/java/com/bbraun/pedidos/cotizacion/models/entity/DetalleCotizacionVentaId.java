package com.bbraun.pedidos.cotizacion.models.entity;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleCotizacionVentaId implements Serializable{

    private String idcotizacion;
    private String idproducto;
}
