package com.facturar.facturacion.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacturaDetalleDTO {
    private String codProducto;
    private Integer cantidad;
}
