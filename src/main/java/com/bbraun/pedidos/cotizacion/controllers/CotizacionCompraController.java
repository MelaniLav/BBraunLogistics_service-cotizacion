package com.bbraun.pedidos.cotizacion.controllers;

import com.bbraun.pedidos.cotizacion.models.dto.CotizacionPorSolicitudDto;
import com.bbraun.pedidos.cotizacion.models.dto.SolicitudCotizacionDTO;
import com.bbraun.pedidos.cotizacion.models.entity.CotizacionCompra;
import com.bbraun.pedidos.cotizacion.models.entity.DetalleCotizacionCompra;
import com.bbraun.pedidos.cotizacion.models.entity.SolicitudCotizacionCompra;
import com.bbraun.pedidos.cotizacion.services.ICotizacionCService;
import com.bbraun.pedidos.cotizacion.services.IDetalleSolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coti-c")
public class CotizacionCompraController {

    @Autowired
    private ICotizacionCService cotizacionCService;

    @Autowired
    private IDetalleSolicitudService detalleSolicitudService;




    @GetMapping("/all")
    public List<SolicitudCotizacionCompra> findAll() {
        return cotizacionCService.findAll();
    }

    @GetMapping("/details")
    public List<DetalleCotizacionCompra> findAllDetails() {
        return detalleSolicitudService.findAll();
    }

    @GetMapping("/all-dto")
    public List<SolicitudCotizacionDTO> findAllDTO() {
        return cotizacionCService.findAllDTO();
    }

    @PostMapping("/create")
    public SolicitudCotizacionCompra createSolicitudWithDetails(@RequestBody SolicitudCotizacionDTO solicitudCotizacionDTO) {
        return cotizacionCService.createSolicitudWithDetails(solicitudCotizacionDTO);
    }

    @GetMapping("/dto/{idSolicitudCompra}")
    public List<CotizacionPorSolicitudDto> findAllDTO(@PathVariable String idSolicitudCompra) {
        return cotizacionCService.findAllCotizacionesBySolicitud(idSolicitudCompra);
    }

    @PostMapping("/update")
    public CotizacionCompra updateCotizacionCompra(@RequestBody CotizacionPorSolicitudDto dto) {
        return cotizacionCService.updateCotizacionCompra(dto);
    }

    @GetMapping("/code/{idSolicitudCompra}")
    public String getCodeCotizacionSolicitud(@PathVariable String idSolicitudCompra) {
        return cotizacionCService.getCodeCotizacionSolicitud(idSolicitudCompra);
    }

}
