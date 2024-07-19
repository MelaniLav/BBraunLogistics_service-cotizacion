package com.bbraun.pedidos.cotizacion.services.impl;

import com.bbraun.pedidos.cotizacion.models.entity.DetalleCotizacionCompra;
import com.bbraun.pedidos.cotizacion.repository.DetalleCotizacionCompraRepository;
import com.bbraun.pedidos.cotizacion.services.IDetalleSolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleSolicitudCotizacionImpl implements IDetalleSolicitudService {


    @Autowired
    private DetalleCotizacionCompraRepository detalleCotizacionCompraRepository;
    @Override
    public List<DetalleCotizacionCompra> findAll() {
        return detalleCotizacionCompraRepository.findAll();
    }
}
