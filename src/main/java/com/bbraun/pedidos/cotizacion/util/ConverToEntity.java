package com.bbraun.pedidos.cotizacion.util;

import com.bbraun.pedidos.cotizacion.models.Producto;
import com.bbraun.pedidos.cotizacion.models.dto.CotizacionVentaDTO;
import com.bbraun.pedidos.cotizacion.models.dto.DetalleCotizacionVentaDTO;
import com.bbraun.pedidos.cotizacion.models.entity.CotizacionVenta;
import com.bbraun.pedidos.cotizacion.models.entity.Departamento;
import com.bbraun.pedidos.cotizacion.models.entity.DetalleCotizacionVenta;
import com.bbraun.pedidos.cotizacion.models.entity.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConverToEntity {

    @Autowired
    private RestTemplate restTemplate;

    public CotizacionVenta convertToEntityCotizacionVenta(CotizacionVentaDTO dto, Estado estado, Departamento departamento){

        return CotizacionVenta.builder()
                .id_cotizacion(dto.getIdcotizacion())
                .id_empleado(dto.getIdempleado())
                .estado(estado)
                .nombre_cliente(dto.getNombrecliente())
                .monto_producto(dto.getMontoproducto())
                .fecha_emision(dto.getFechaemision())
                .email(dto.getEmail())
                .monto_impuesto(dto.getMontoimpuesto())
                .monto_total(dto.getMontototal())
                .dni(dto.getDni())
                .id_departamento(departamento)
                .build();
    }

    public List<DetalleCotizacionVenta> converToEntityDetalleVenta(List<DetalleCotizacionVentaDTO> dto, CotizacionVenta cotizacionVenta) {
        return dto.stream().map(detalle -> {
            String url = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/api/almacen/producto/buscar-producto")
                    .queryParam("nombre", detalle.getProducto())
                    .queryParam("concentracion", detalle.getConcentracion())
                    .toUriString();

            Producto producto = restTemplate.getForObject(url, Producto.class);

            return DetalleCotizacionVenta.builder()
                    .idcotizacion(cotizacionVenta.getId_cotizacion())
                    .idproducto(producto != null ? producto.getCode() : null)
                    .monto(detalle.getTotal())
                    .cantidad(detalle.getCantidad())
                    .build();
        }).collect(Collectors.toList());
    }
}
