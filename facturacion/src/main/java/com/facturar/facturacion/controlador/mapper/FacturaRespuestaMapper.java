package com.facturar.facturacion.controlador.mapper;

import com.facturar.facturacion.dto.FacturaRespuestaDTO;
import com.facturar.facturacion.modelo.Factura;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FacturaRespuestaMapper {

    @Mapping(target = "fecha", source = "fecha", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    FacturaRespuestaDTO toDto(Factura factura);
}
