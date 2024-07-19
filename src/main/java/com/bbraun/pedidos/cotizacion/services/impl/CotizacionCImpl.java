package com.bbraun.pedidos.cotizacion.services.impl;

import com.bbraun.pedidos.cotizacion.models.Proveedor;
import com.bbraun.pedidos.cotizacion.models.SolicitudCompra;
import com.bbraun.pedidos.cotizacion.models.dto.CotizacionPorSolicitudDto;
import com.bbraun.pedidos.cotizacion.models.dto.DetalleCotizacionCompraDTO;
import com.bbraun.pedidos.cotizacion.models.dto.SolicitudCotizacionDTO;
import com.bbraun.pedidos.cotizacion.models.entity.CotizacionCompra;
import com.bbraun.pedidos.cotizacion.models.entity.DetalleCotizacionCompra;
import com.bbraun.pedidos.cotizacion.models.entity.Estado;
import com.bbraun.pedidos.cotizacion.models.entity.SolicitudCotizacionCompra;
import com.bbraun.pedidos.cotizacion.repository.CotizacionCompraRepository;
import com.bbraun.pedidos.cotizacion.repository.DetalleCotizacionCompraRepository;
import com.bbraun.pedidos.cotizacion.repository.EstadoRepository;
import com.bbraun.pedidos.cotizacion.repository.SolicitudCotizacionRepository;
import com.bbraun.pedidos.cotizacion.services.ICotizacionCService;
import com.bbraun.pedidos.cotizacion.util.CodigoGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CotizacionCImpl implements ICotizacionCService{

    @Autowired
    private SolicitudCotizacionRepository solicitudCotizacionRepository;

    @Autowired
    private DetalleCotizacionCompraRepository detalleCotizacionCompraRepository;

    @Autowired
    private CodigoGeneratorService codigoGeneratorService;

    @Autowired
    private CotizacionCompraRepository cotizacionCompraRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<SolicitudCotizacionCompra> findAll() {
        return solicitudCotizacionRepository.findAll();
    }

    @Override
    public List<SolicitudCotizacionDTO> findAllDTO() {
        List<SolicitudCotizacionCompra> solicitudes = solicitudCotizacionRepository.findAll();
        List<SolicitudCotizacionDTO> solicitudesDTO = new ArrayList<>();

        solicitudes.stream().forEach(solicitud -> {
            SolicitudCotizacionDTO solicitudDTO = SolicitudCotizacionDTO.builder()
                            .idsolicitudcompra(solicitud.getIdSolicitudCompra())
                                    .idsolicitudcotizacion(solicitud.getIdSolicitudCotizacion())
                                            .fechacreacion(solicitud.getFechacreacion())
                    .detalles(converToDetalleSolicitudDto(detalleCotizacionCompraRepository.findAllByIdSolicitudCotizacion(solicitud.getIdSolicitudCotizacion())))
                    .build();
            solicitudesDTO.add(solicitudDTO);
        });

        return solicitudesDTO;
    }

    @Override
    public SolicitudCotizacionCompra createSolicitudWithDetails(SolicitudCotizacionDTO solicitudCotizacionDTO) {
        String nuevoCodigo = codigoGeneratorService.generateCodigo();

        SolicitudCotizacionCompra solicitud_cotizacion = SolicitudCotizacionCompra.builder()
                .idSolicitudCotizacion(nuevoCodigo)
                .idSolicitudCompra(solicitudCotizacionDTO.getIdsolicitudcompra())
                .fechacreacion(solicitudCotizacionDTO.getFechacreacion())
                .build();

        SolicitudCotizacionCompra solicitud_created = solicitudCotizacionRepository.save(solicitud_cotizacion);
        if(solicitud_created != null){
            List<DetalleCotizacionCompraDTO> detalles = solicitudCotizacionDTO.getDetalles();
            detalles.forEach(detalle -> {
                DetalleCotizacionCompra detalle_cotizacion = DetalleCotizacionCompra.builder()
                        .idSolicitudCotizacion(solicitud_created.getIdSolicitudCotizacion())
                        .idProveedor(detalle.getIdproveedor())
                        .build();
                detalleCotizacionCompraRepository.save(detalle_cotizacion);
            });
        }

        return solicitud_created;
    }

    private List<DetalleCotizacionCompraDTO> converToDetalleSolicitudDto(List<DetalleCotizacionCompra> dtos){
        List<DetalleCotizacionCompraDTO> detalles = new ArrayList<>();
        dtos.stream().forEach(dto -> {
            DetalleCotizacionCompraDTO detalle = DetalleCotizacionCompraDTO.builder()
                    .idsolicitudcotizacion(dto.getIdSolicitudCotizacion())
                    .idproveedor(dto.getIdProveedor())
                    .build();
            detalles.add(detalle);
        });
        return detalles;
    }

    @Override
    public List<CotizacionPorSolicitudDto> findAllCotizacionesBySolicitud(String idSolicitudCompra) {
        System.out.println("idSolicitudCompra: " + idSolicitudCompra);
        SolicitudCotizacionCompra solicitudCotizacion = solicitudCotizacionRepository.findByIdSolicitudCompra(idSolicitudCompra);
        List<DetalleCotizacionCompra> detalles = detalleCotizacionCompraRepository.findAllByIdSolicitudCotizacion(solicitudCotizacion.getIdSolicitudCotizacion());

        detalles.forEach(detalle -> {
            System.out.println("idsolicitudcotizacion: " + detalle.getIdSolicitudCotizacion() + " idProveedor: " + detalle.getIdProveedor());
        });

        List<CotizacionCompra> cotizaciones = new ArrayList<>();

        detalles.forEach(detalle -> {
            CotizacionCompra cotizacion = cotizacionCompraRepository.findByIdSolicitudCotizacionAndIdProveedor(detalle.getIdSolicitudCotizacion(), detalle.getIdProveedor());
            if (cotizacion != null) {
                cotizaciones.add(cotizacion);
            } else {
                System.out.println("Cotizacion not found for idSolicitudCotizacion: " + detalle.getIdSolicitudCotizacion() + " and idProveedor: " + detalle.getIdProveedor());
            }
        });

        List<CotizacionPorSolicitudDto> cotizacionesDTO = new ArrayList<>();
        cotizaciones.forEach(cotizacion -> {
            try {
                System.out.println("cotizacion: aaa " + cotizacion.getIdSolicitudCotizacion() + " idProveedor aaa: " + cotizacion.getIdProveedor());
                String idProveedor = cotizacion.getIdProveedor();
                Proveedor proveedor = restTemplate.getForObject("http://localhost:9000/api/proveedor/proveedor/get/" + idProveedor, Proveedor.class);
                if (proveedor != null) {
                    CotizacionPorSolicitudDto cotizacionDTO = CotizacionPorSolicitudDto.builder()
                            .idsolicitudcompra(idSolicitudCompra)
                            .idproveedor(proveedor.getIdproveedor())
                            .nombreProveedor(proveedor.getEmpresa())
                            .fechaEntregaCotizacion(cotizacion.getFechaEntrega())
                            .monto(cotizacion.getMonto())
                            .estado(cotizacion.getEstado().getEstado())
                            .rate(proveedor.getRate())
                            .build();
                    cotizacionesDTO.add(cotizacionDTO);
                } else {
                    System.out.println("Proveedor not found for idProveedor: " + idProveedor);
                }
            } catch (Exception e) {
                System.out.println("Error processing cotizacion: " + e.getMessage());
            }
        });

        Integer pendiente=0, aceptado=0, rechazado = 0;

        for (int i=0; i<cotizaciones.size(); i++) {
            if(cotizaciones.get(i).getEstado().getEstado().equals("Pendiente")){
                pendiente++;
            }else if(cotizaciones.get(i).getEstado().getEstado().equals("Aceptado")) {
                aceptado++;
            }else if(cotizaciones.get(i).getEstado().getEstado().equals("Rechazado")) {
                rechazado++;
            }
        }

        if (pendiente == cotizaciones.size() || (aceptado== 0 && rechazado<cotizaciones.size())){
            updateEstadoSolicitud(idSolicitudCompra,1);

        }else if (aceptado >= 1) {
            updateEstadoSolicitud(idSolicitudCompra,2);
        }else if (rechazado == cotizaciones.size()) {
            updateEstadoSolicitud(idSolicitudCompra,3);
        }


        return cotizacionesDTO;
    }

    @Override
    public CotizacionCompra updateCotizacionCompra(CotizacionPorSolicitudDto dto) {
        Proveedor proveedor = restTemplate.getForObject("http://localhost:9000/api/proveedor/proveedor/getByNombre/"+dto.getNombreProveedor(), Proveedor.class);

        SolicitudCotizacionCompra solicitud = solicitudCotizacionRepository.findByIdSolicitudCompra(dto.getIdsolicitudcompra());

        CotizacionCompra cotizacion = cotizacionCompraRepository.findByIdSolicitudCotizacionAndIdProveedor(solicitud.getIdSolicitudCotizacion(), proveedor.getIdproveedor());

        Estado estado = estadoRepository.findByEstado(dto.getEstado());
        CotizacionCompra cotizacion_update = CotizacionCompra.builder()
                .idSolicitudCotizacion(cotizacion.getIdSolicitudCotizacion())
                .idProveedor(cotizacion.getIdProveedor())
                .monto(dto.getMonto())
                .fechaEntrega(dto.getFechaEntregaCotizacion())
                .estado(estado)
                .build();
        cotizacionCompraRepository.save(cotizacion_update);
        updateSolicitudCompraEstado(solicitud);
        return cotizacion_update;
    }

    @Override
    public String getCodeCotizacionSolicitud(String idSolicitudCompra) {
        SolicitudCotizacionCompra solicitudCotizacion = solicitudCotizacionRepository.findByIdSolicitudCompra(idSolicitudCompra);
        return solicitudCotizacion.getIdSolicitudCotizacion();
    }

    private void updateSolicitudCompraEstado(SolicitudCotizacionCompra solicitud) {
        List<CotizacionCompra> cotizaciones = cotizacionCompraRepository.findAllByIdSolicitudCotizacion(solicitud.getIdSolicitudCotizacion());
        Integer pendiente = 0, aceptado = 0, rechazado = 0;

        for (CotizacionCompra cotizacion : cotizaciones) {
            if (cotizacion.getEstado().getEstado().equals("Pendiente")) {
                pendiente++;
            } else if (cotizacion.getEstado().getEstado().equals("Aceptado")) {
                aceptado++;
            } else if (cotizacion.getEstado().getEstado().equals("Rechazado")) {
                rechazado++;
            }
        }

        if (pendiente == cotizaciones.size() || (aceptado == 0 && rechazado < cotizaciones.size())) {
            updateEstadoSolicitud(solicitud.getIdSolicitudCompra(), 1);
        } else if (aceptado >= 1) {
            updateEstadoSolicitud(solicitud.getIdSolicitudCompra(), 2);
        } else if (rechazado == cotizaciones.size()) {
            updateEstadoSolicitud(solicitud.getIdSolicitudCompra(), 3);
        }
    }

    private void updateEstadoSolicitud(String idSolictud, Integer estado){
        String url = "http://localhost:9000/api/doc-compra/solicitud/actualizarestado/{idSolicitud}/{estado}";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<SolicitudCompra> response = restTemplate.exchange(url, org.springframework.http.HttpMethod.PUT, requestEntity, SolicitudCompra.class, idSolictud, estado);
        System.out.println("response: " + response.getBody().getIdsolicitudcompra()+ " estado: " + response.getBody().getEstado().getEstado());

    }

}
