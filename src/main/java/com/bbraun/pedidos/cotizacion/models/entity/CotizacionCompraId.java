package com.bbraun.pedidos.cotizacion.models.entity;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CotizacionCompraId implements Serializable {
    private String idSolicitudCotizacion;
    private String idProveedor;
}
