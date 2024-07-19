package com.bbraun.pedidos.cotizacion.controllers;

import com.bbraun.pedidos.cotizacion.models.dto.CotizacionCompraDto;
import com.bbraun.pedidos.cotizacion.models.dto.CotizacionProveedorDto;
import com.bbraun.pedidos.cotizacion.models.entity.CotizacionCompra;
import com.bbraun.pedidos.cotizacion.services.ICotizacionCompra;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cotizacion-proveedor")
public class CotizacionProveedorController {

    @Autowired
    private ICotizacionCompra cotizacionCompraService;

    @PostMapping("/guardar")
    public CotizacionCompra guardar(@RequestBody CotizacionProveedorDto dto){
        return cotizacionCompraService.save(dto);
    }


}
