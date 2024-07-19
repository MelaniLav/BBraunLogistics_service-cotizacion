package com.bbraun.pedidos.cotizacion.repository;

import com.bbraun.pedidos.cotizacion.models.entity.DetalleCotizacionCompra;
import com.bbraun.pedidos.cotizacion.models.entity.DetalleCotizacionCompraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleCotizacionCompraRepository extends JpaRepository<DetalleCotizacionCompra, DetalleCotizacionCompraId> {

    List<DetalleCotizacionCompra> findAll();

    List<DetalleCotizacionCompra> findAllByIdSolicitudCotizacion(String code);
}
