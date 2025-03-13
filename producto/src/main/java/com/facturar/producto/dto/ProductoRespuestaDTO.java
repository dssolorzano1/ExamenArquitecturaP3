package com.facturar.producto.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class ProductoRespuestaDTO {
    private String codProducto;
    private String nombre;
    private Integer existencia;
    private BigDecimal precio;
}
