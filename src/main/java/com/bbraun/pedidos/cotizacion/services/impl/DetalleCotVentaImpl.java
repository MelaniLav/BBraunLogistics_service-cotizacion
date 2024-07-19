package com.bbraun.pedidos.cotizacion.services.impl;

import com.bbraun.pedidos.cotizacion.models.Producto;
import com.bbraun.pedidos.cotizacion.models.dto.DetalleCotizacionVentaDTO;
import com.bbraun.pedidos.cotizacion.models.entity.DetalleCotizacionVenta;
import com.bbraun.pedidos.cotizacion.models.entity.DetalleCotizacionVentaId;
import com.bbraun.pedidos.cotizacion.repository.DetalleCVentaRepository;
import com.bbraun.pedidos.cotizacion.services.IDetalleCotiVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class DetalleCotVentaImpl implements IDetalleCotiVenta {

    @Autowired
    private DetalleCVentaRepository detalleCotizacionVentaRepository;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public List<DetalleCotizacionVenta> findAll() {
        return detalleCotizacionVentaRepository.findAll();
    }

    @Override
    public Producto findDetailsProduct(String idProducto) {
        Producto producto = restTemplate.getForObject("http://localhost:9000/api/almacen/producto/buscar-producto/"+idProducto, Producto.class);
        return producto;
    }

    @Override
    public void delete(DetalleCotizacionVentaDTO dto) {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/api/almacen/producto/buscar-producto")
                .queryParam("nombre", dto.getProducto())
                .queryParam("concentracion", dto.getConcentracion())
                .toUriString();

        Producto producto = restTemplate.getForObject(url, Producto.class);
        DetalleCotizacionVentaId id = new DetalleCotizacionVentaId(dto.getIdcotizacion(),producto.getCode());
        detalleCotizacionVentaRepository.deleteById(id);
    }
}
