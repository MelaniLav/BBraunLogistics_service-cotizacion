package com.bbraun.pedidos.cotizacion.repository;

import com.bbraun.pedidos.cotizacion.models.entity.CotizacionVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotizacionVentaRepository extends JpaRepository<CotizacionVenta,String> {

    @Query(value = "SELECT c.id_cotizacion FROM CotizacionVenta c ORDER BY c.id_cotizacion DESC")
    List<String> findLastCode();

    @Query("SELECT c FROM CotizacionVenta c WHERE c.id_cotizacion = ?1")
    CotizacionVenta findByIdCotizacion(String idCotizacion);
}
