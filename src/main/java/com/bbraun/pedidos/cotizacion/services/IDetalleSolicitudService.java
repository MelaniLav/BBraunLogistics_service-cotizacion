package com.bbraun.pedidos.cotizacion.services;

import com.bbraun.pedidos.cotizacion.models.entity.DetalleCotizacionCompra;

import java.util.List;

public interface IDetalleSolicitudService {

    List<DetalleCotizacionCompra> findAll();
}
