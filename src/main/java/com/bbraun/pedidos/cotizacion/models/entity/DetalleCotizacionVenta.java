package com.bbraun.pedidos.cotizacion.models.entity;

import jakarta.persistence.*;
import lombok.*;

@IdClass(DetalleCotizacionVentaId.class)
@Table(name = "detalles_cotizacion_venta")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleCotizacionVenta {

    @Id
    private String idcotizacion;
    @Id
    private String idproducto;

    private Integer cantidad;

    private Float monto;

}
