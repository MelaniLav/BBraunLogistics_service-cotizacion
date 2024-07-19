package com.bbraun.pedidos.cotizacion.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cotizaciones_venta")
@Builder
public class CotizacionVenta {

    @Id
    private String id_cotizacion;
    private String id_empleado;

    @OneToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;

    private String nombre_cliente;
    private Float monto_producto;
    private Date fecha_emision;
    private String email;
    private Float monto_impuesto;
    private Float monto_total;

    @OneToOne
    @JoinColumn(name = "id_departamento")
    private Departamento id_departamento;

    private String dni;


}
