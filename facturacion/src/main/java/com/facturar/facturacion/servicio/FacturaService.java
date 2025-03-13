package com.facturar.facturacion.servicio;

import com.facturar.facturacion.dto.FacturaCabeceraDTO;
import com.facturar.facturacion.dto.FacturaDetalleDTO;
import com.facturar.facturacion.modelo.Factura;
import com.facturar.facturacion.repositorio.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public Factura crearFactura(FacturaCabeceraDTO cabecera) {
        
        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal iva = BigDecimal.ZERO;
        BigDecimal total = BigDecimal.ZERO;

        for (FacturaDetalleDTO detalle : cabecera.getDetalles()) {
            BigDecimal precioUnitario = BigDecimal.valueOf(20.00); // Simulación
            BigDecimal cantidad = BigDecimal.valueOf(detalle.getCantidad());

            BigDecimal sub = precioUnitario.multiply(cantidad);
            BigDecimal ivaDetalle = sub.multiply(BigDecimal.valueOf(0.12)); // 12% IVA

            subtotal = subtotal.add(sub);
            iva = iva.add(ivaDetalle);
        }

        total = subtotal.add(iva);

        Factura factura = new Factura(
                cabecera.getTipoIdentificacion(),
                cabecera.getIdentificacion(),
                cabecera.getNombre(),
                LocalDateTime.now(),
                subtotal,
                iva,
                total
        );

        factura.setCodFactura(UUID.randomUUID().toString());

        return facturaRepository.save(factura);
    }
}
