package com.bbraun.pedidos.cotizacion.models;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lote {

    private String code;
    private String operativeStatus;
    private String disponibilityState;
    private String securityState;
    private Integer stock;
    private Date expiredDate;
}
