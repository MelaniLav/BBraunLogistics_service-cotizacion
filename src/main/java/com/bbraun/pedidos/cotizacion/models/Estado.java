package com.bbraun.pedidos.cotizacion.models;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estado {


    private Integer idestado;


    private String estado;

}
