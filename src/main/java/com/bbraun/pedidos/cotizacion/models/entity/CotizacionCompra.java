package com.bbraun.pedidos.cotizacion.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@IdClass(CotizacionCompraId.class)
@Entity
@Table(name = "cotizaciones_compra")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CotizacionCompra {

    @Id
    @Column(name = "id_solicitud_cotizacion")
    private String idSolicitudCotizacion;
    @Id
    @Column(name = "id_proveedor")
    private String idProveedor;
    @OneToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;
    private Float monto;
    @Column(name = "fecha_entrega")
    private Date fechaEntrega;
}
