package com.bbraun.pedidos.cotizacion.repository;

import com.bbraun.pedidos.cotizacion.models.dto.SolicitudCotizacionDTO;
import com.bbraun.pedidos.cotizacion.models.entity.SolicitudCotizacionCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudCotizacionRepository extends JpaRepository<SolicitudCotizacionCompra, String>{


    List<SolicitudCotizacionCompra> findAll();

    SolicitudCotizacionCompra findByIdSolicitudCompra(String idSolicitudCotizacion);

    @Query("SELECT s.idSolicitudCotizacion from SolicitudCotizacionCompra s order by s.idSolicitudCotizacion desc")
    List<String> getLastCode();

    @Query("SELECT s.idSolicitudCotizacion from SolicitudCotizacionCompra s where s.idSolicitudCompra = ?1")
    List<String> findBySolicitudCompra(String idSolicitudCompra);
}
