package com.bbraun.pedidos.cotizacion.models;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudCompra {


    private String idsolicitudcompra;


    private Estado estado;

    private String idasistentealmacen;
    private String idasistentecompra;
    private String nombreproducto;
    private Integer cantidad;
    private Date fecha_entrega;
}
