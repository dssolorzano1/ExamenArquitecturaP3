package com.facturar.facturacion.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class FacturaRespuestaDTO {
    private String codFactura;
    private String tipoIdentificacion;
    private String identificacion;
    private String nombre;
    private LocalDateTime fecha;
    private BigDecimal subtotal;
    private BigDecimal iva;
    private BigDecimal total;
}
