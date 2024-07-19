package com.bbraun.pedidos.cotizacion.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "estados")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estado {

    @Id
    private Integer idestado;
    private String estado;
}
