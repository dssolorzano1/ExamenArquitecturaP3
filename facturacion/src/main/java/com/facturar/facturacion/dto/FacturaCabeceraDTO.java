package com.facturar.facturacion.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class FacturaCabeceraDTO {
    private String tipoIdentificacion;
    private String identificacion;
    private String nombre;
    private List<FacturaDetalleDTO> detalles;
}
