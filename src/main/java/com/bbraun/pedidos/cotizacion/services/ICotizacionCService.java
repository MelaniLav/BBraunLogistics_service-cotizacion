package com.bbraun.pedidos.cotizacion.services;

import com.bbraun.pedidos.cotizacion.models.dto.CotizacionPorSolicitudDto;
import com.bbraun.pedidos.cotizacion.models.dto.SolicitudCotizacionDTO;
import com.bbraun.pedidos.cotizacion.models.entity.CotizacionCompra;
import com.bbraun.pedidos.cotizacion.models.entity.SolicitudCotizacionCompra;

import java.util.List;

public interface ICotizacionCService {

    public List<SolicitudCotizacionCompra> findAll();

    public List<SolicitudCotizacionDTO> findAllDTO();

    public SolicitudCotizacionCompra createSolicitudWithDetails(SolicitudCotizacionDTO solicitudCotizacionDTO);

    public List<CotizacionPorSolicitudDto> findAllCotizacionesBySolicitud(String idSolicitudCompra);

    public CotizacionCompra updateCotizacionCompra(CotizacionPorSolicitudDto dto);

    public String getCodeCotizacionSolicitud(String idSolicitudCompra);
}
