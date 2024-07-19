package com.bbraun.pedidos.cotizacion.repository;

import com.bbraun.pedidos.cotizacion.models.entity.DetalleCotizacionVenta;
import com.bbraun.pedidos.cotizacion.models.entity.DetalleCotizacionVentaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DetalleCVentaRepository extends JpaRepository<DetalleCotizacionVenta, DetalleCotizacionVentaId> {

    public List<DetalleCotizacionVenta> findAllByIdcotizacion(String idcotizacion);

    @Transactional
    @Modifying
    @Query("delete from DetalleCotizacionVenta d where d.idcotizacion = :idCotizacion")
    void deleteByIdCotizacion(String idCotizacion);
}
