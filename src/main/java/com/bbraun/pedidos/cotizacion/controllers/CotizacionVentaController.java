package com.bbraun.pedidos.cotizacion.controllers;

import com.bbraun.pedidos.cotizacion.models.dto.CotizacionDtoPDF;
import com.bbraun.pedidos.cotizacion.models.dto.CotizacionVentaDTO;
import com.bbraun.pedidos.cotizacion.models.dto.DetalleCotizacionVentaDTO;
import com.bbraun.pedidos.cotizacion.models.entity.CotizacionVenta;
import com.bbraun.pedidos.cotizacion.models.entity.DetalleCotizacionVenta;
import com.bbraun.pedidos.cotizacion.services.ICotizacionVService;
import com.bbraun.pedidos.cotizacion.services.IDetalleCotiVenta;
import com.bbraun.pedidos.cotizacion.services.impl.PdfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coti-v")
public class CotizacionVentaController {

    private static final Logger logger = LoggerFactory.getLogger(CotizacionVentaController.class);

    @Autowired
    private ICotizacionVService cotizacionVService;

    @Autowired
    private IDetalleCotiVenta detalleCotiVenta;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/buscar-coti/{id}")
    public CotizacionVenta buscarCotizacion(@PathVariable("id") String id){
        return cotizacionVService.findById(id);
    }

    @GetMapping("/buscar-coti-dto/{id}")
    public CotizacionVentaDTO buscarCotizacionDto(@PathVariable("id") String id){
        return cotizacionVService.getById(id);
    }

    @GetMapping("/all")
    public List<CotizacionVenta> findAll(){
        return cotizacionVService.findAll();
    }

    @GetMapping("/details")
    public List<DetalleCotizacionVenta> findDetails(){
        return  detalleCotiVenta.findAll();
    }

    @GetMapping("/full")
    public List<CotizacionDtoPDF> findFull(){
        return  cotizacionVService.findAllCotizaciones();
    }

    @GetMapping("/last-code")
    public String lastCode(){
        return cotizacionVService.lastCode();
    }
    @GetMapping("/details-full")
    public List<CotizacionVentaDTO> listCotiDetails(){
        return cotizacionVService.findAllWithDetails();
    }

    @PostMapping("/create")
    public ResponseEntity<CotizacionVenta> createCotizacion(@RequestBody CotizacionVentaDTO dto){
        CotizacionVenta cotizacionVenta = cotizacionVService.createCotizacionVentaWithDetails(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/buscar-coti/{id}")
                .buildAndExpand(cotizacionVenta.getId_cotizacion())
                .toUri();
        return ResponseEntity.created(location).body(cotizacionVenta);
    }

    @PostMapping("/calculate")
    public CotizacionVentaDTO calculateMontos(@RequestBody CotizacionVentaDTO dto){
        return cotizacionVService.calculateMontos(dto);
    }

    @PutMapping("/update")
    public CotizacionVenta updateCotizacion(@RequestBody CotizacionVentaDTO dto){
        return cotizacionVService.updateCotizacionVentaWithDetails(dto);
    }

    @DeleteMapping("/delete/detalle")
    public void deleteCotizacion(@RequestBody DetalleCotizacionVentaDTO dto){
       detalleCotiVenta.delete(dto);
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> getCotizacionPdf(@PathVariable String id){
        try {
            CotizacionDtoPDF cotizacion = cotizacionVService.findCotizacionById(id);
            if(cotizacion == null){
                logger.warn("Cotización no encontrada con el ID: " + id);
                return ResponseEntity.notFound().build();
            }
            byte[] pdf = pdfService.generatePdfFromTemplate(cotizacion, "static/pdf/cotizacion_venta.pdf");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "cotizacion.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdf);
        } catch (IOException e) {
            logger.error("Error al generar el PDF: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
