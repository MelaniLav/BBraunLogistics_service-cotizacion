package com.bbraun.pedidos.cotizacion.services;


import com.bbraun.pedidos.cotizacion.models.dto.CotizacionCompraDto;
import com.bbraun.pedidos.cotizacion.models.dto.CotizacionProveedorDto;
import com.bbraun.pedidos.cotizacion.models.entity.CotizacionCompra;

import java.util.List;

public interface ICotizacionCompra {

    public List<CotizacionCompraDto> listAll();

    public CotizacionCompra save(CotizacionProveedorDto dto);
}
