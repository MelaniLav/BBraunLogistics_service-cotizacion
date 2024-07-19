package com.bbraun.pedidos.cotizacion.repository;

import com.bbraun.pedidos.cotizacion.models.dto.CotizacionPorSolicitudDto;
import com.bbraun.pedidos.cotizacion.models.entity.CotizacionCompra;
import com.bbraun.pedidos.cotizacion.models.entity.CotizacionCompraId;
import com.bbraun.pedidos.cotizacion.models.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotizacionCompraRepository extends JpaRepository<CotizacionCompra, CotizacionCompraId> {

    @Query(value = "SELECT p.empresa AS nombre_proveedor, " +
            "cc.fecha_entrega AS fecha_entrega_cotizacion, " +
            "cc.monto, " +
            "e.estado, " +
            "p.rate " +
            "FROM solicitudes_compra sc " +
            "JOIN solicitudes_cotizacion sct ON sc.idSolicitudCompra = sct.id_solicitud_compra " +
            "JOIN detalles_cotizacion_compra dcc ON sct.id_solicitud_cotizacion = dcc.id_solicitud_cotizacion " +
            "JOIN cotizaciones_compra cc ON dcc.id_solicitud_cotizacion = cc.id_solicitud_cotizacion AND dcc.id_proveedor = cc.id_proveedor " +
            "JOIN proveedores p ON dcc.id_proveedor = p.idProveedor " +
            "JOIN estados e ON cc.id_estado = e.idEstado " +
            "WHERE sc.idSolicitudCompra = :idSolicitudCompra",
            nativeQuery = true)
    List<CotizacionPorSolicitudDto> findCotizacionesBySolicitudCompra(@Param("idSolicitudCompra") String idSolicitudCompra);

    CotizacionCompra findByIdSolicitudCotizacionAndIdProveedor(String idSolicitudCotizacion, String idProveedor);

    List<CotizacionCompra> findAllByIdSolicitudCotizacion(String idSolicitudCotizacion);
}
