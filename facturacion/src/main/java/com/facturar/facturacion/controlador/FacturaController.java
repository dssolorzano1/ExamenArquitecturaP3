package com.facturar.facturacion.controlador;

import com.facturar.facturacion.dto.FacturaCabeceraDTO;
import com.facturar.facturacion.dto.FacturaRespuestaDTO;
import com.facturar.facturacion.modelo.Factura;
import com.facturar.facturacion.servicio.FacturaService;
import com.facturar.facturacion.controlador.mapper.FacturaRespuestaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private FacturaRespuestaMapper facturaRespuestaMapper;
    @PostMapping
    public ResponseEntity<FacturaRespuestaDTO> crearFactura(@RequestBody FacturaCabeceraDTO cabecera) {
        Factura factura = facturaService.crearFactura(cabecera);
        return ResponseEntity.ok(facturaRespuestaMapper.toDto(factura));
    }
}
