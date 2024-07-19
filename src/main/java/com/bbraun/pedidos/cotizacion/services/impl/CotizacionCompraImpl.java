package com.bbraun.pedidos.cotizacion.services.impl;

import com.bbraun.pedidos.cotizacion.models.dto.CotizacionCompraDto;
import com.bbraun.pedidos.cotizacion.models.dto.CotizacionProveedorDto;
import com.bbraun.pedidos.cotizacion.models.entity.CotizacionCompra;
import com.bbraun.pedidos.cotizacion.models.entity.Estado;
import com.bbraun.pedidos.cotizacion.repository.CotizacionCompraRepository;
import com.bbraun.pedidos.cotizacion.repository.EstadoRepository;
import com.bbraun.pedidos.cotizacion.services.ICotizacionCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CotizacionCompraImpl implements ICotizacionCompra {

    @Autowired
    private CotizacionCompraRepository cotizacionCompraRepository;

    @Autowired
    private EstadoRepository estadoRepository;
    @Override
    public List<CotizacionCompraDto> listAll() {
        List<CotizacionCompra> cotizacionCompras = cotizacionCompraRepository.findAll();
        List<CotizacionCompraDto> cotizacionCompraDtos = new ArrayList<>();

        cotizacionCompras.forEach(cotizacionCompra -> {
            Estado estado = cotizacionCompra.getEstado();
            CotizacionCompraDto cotizacionCompraDto = CotizacionCompraDto.builder()
                    .codigo_solicitud_cotizacion(cotizacionCompra.getIdSolicitudCotizacion())
                    .codigo_proveedor(cotizacionCompra.getIdProveedor())
                    .estado(estado.getEstado())
                    .monto(cotizacionCompra.getMonto())
                    .fecha_entrega(cotizacionCompra.getFechaEntrega().toString())
                    .build();
            cotizacionCompraDtos.add(cotizacionCompraDto);
        });
        return cotizacionCompraDtos;
    }

    @Override
    public CotizacionCompra save(CotizacionProveedorDto dto) {
        // Validar que la solicitud de cotización y el proveedor existen



        Estado estado = estadoRepository.findByEstado("Pendiente");
        if (estado == null) {
            throw new RuntimeException("Estado no encontrado: " + "Pendiente");
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaEntrega;
        try {
            fechaEntrega = formatter.parse(dto.getFecha_entrega());
        } catch (ParseException e) {
            throw new RuntimeException("Formato de fecha incorrecto: " + dto.getFecha_entrega());
        }

        CotizacionCompra cotizacionCompra = CotizacionCompra.builder()
                .idSolicitudCotizacion(dto.getCodigo_solicitud_cotizacion())
                .idProveedor(dto.getCodigo_proveedor())
                .estado(estado)
                .monto(dto.getMonto())
                .fechaEntrega(fechaEntrega)
                .build();

        return cotizacionCompraRepository.save(cotizacionCompra);
    }
}
