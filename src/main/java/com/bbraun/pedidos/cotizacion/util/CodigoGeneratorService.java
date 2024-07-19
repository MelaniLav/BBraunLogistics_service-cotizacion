package com.bbraun.pedidos.cotizacion.util;

import com.bbraun.pedidos.cotizacion.repository.SolicitudCotizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodigoGeneratorService {

    private static final String PREFIX = "SC-";

    @Autowired
    private SolicitudCotizacionRepository solicitudCotizacionRepository;

    public String generateCodigo(){
        String ultimoCodigo = solicitudCotizacionRepository.getLastCode().get(0);

        int numeroActual = extraerNumero(ultimoCodigo);
        int siguienteNumero = numeroActual + 1;

        return PREFIX + String.format("%03d", siguienteNumero);
    }

    private int extraerNumero(String codigo){
        return Integer.parseInt(codigo.substring(PREFIX.length()));
    }
}
