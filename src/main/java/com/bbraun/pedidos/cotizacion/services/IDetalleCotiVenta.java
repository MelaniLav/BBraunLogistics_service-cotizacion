package com.bbraun.pedidos.cotizacion.services;

import com.bbraun.pedidos.cotizacion.models.Producto;
import com.bbraun.pedidos.cotizacion.models.dto.DetalleCotizacionVentaDTO;
import com.bbraun.pedidos.cotizacion.models.entity.DetalleCotizacionVenta;

import java.util.List;

public interface IDetalleCotiVenta {

    public List<DetalleCotizacionVenta> findAll();

    public Producto findDetailsProduct(String idProducto);

    public void delete(DetalleCotizacionVentaDTO dto);
}
