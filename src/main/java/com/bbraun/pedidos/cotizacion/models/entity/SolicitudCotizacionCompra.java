package com.bbraun.pedidos.cotizacion.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "solicitudes_cotizacion")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolicitudCotizacionCompra {

    @Id
    @Column(name = "id_solicitud_cotizacion")
    private String idSolicitudCotizacion;
    @Column(name = "id_solicitud_compra")
    private String idSolicitudCompra;
    @Column(name = "fecha_creacion")
    private Date fechacreacion;
}
