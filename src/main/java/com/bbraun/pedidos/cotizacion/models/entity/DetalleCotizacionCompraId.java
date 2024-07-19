package com.bbraun.pedidos.cotizacion.models.entity;

import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleCotizacionCompraId implements Serializable {

    private String idSolicitudCotizacion;
    private String idProveedor;
}
