package com.bbraun.pedidos.cotizacion.models.entity;

import jakarta.persistence.*;
import lombok.*;

@IdClass(DetalleCotizacionCompraId.class)
@Entity
@Table(name = "detalles_cotizacion_compra")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleCotizacionCompra {

    @Id
    @Column(name = "id_solicitud_cotizacion")
    private String idSolicitudCotizacion;

    @Id
    @Column(name = "id_proveedor")
    private String idProveedor;
}
