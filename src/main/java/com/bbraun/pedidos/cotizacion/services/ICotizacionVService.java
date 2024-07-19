package com.bbraun.pedidos.cotizacion.services;

import com.bbraun.pedidos.cotizacion.models.dto.CotizacionDtoPDF;
import com.bbraun.pedidos.cotizacion.models.dto.CotizacionVentaDTO;
import com.bbraun.pedidos.cotizacion.models.entity.CotizacionVenta;

import java.util.List;

public interface ICotizacionVService {

    public List<CotizacionVenta> findAll();

    public CotizacionVenta findById(String id);

    public String lastCode();

    public CotizacionVentaDTO getById(String id);
    public CotizacionDtoPDF findCotizacionById(String idcotizacion);

    public List<CotizacionDtoPDF> findAllCotizaciones();

    public List<CotizacionVentaDTO> findAllWithDetails();

    public CotizacionVenta createCotizacionVentaWithDetails(CotizacionVentaDTO cotizacionVentaDTO);

    public CotizacionVentaDTO calculateMontos(CotizacionVentaDTO cotizacionVentaDTO);

    public CotizacionVenta updateCotizacionVentaWithDetails(CotizacionVentaDTO cotizacionVentaDTO);
}
