package com.facturar.producto.controlador.mapper;

import com.facturar.producto.dto.ProductoRespuestaDTO;
import com.facturar.producto.modelo.Producto;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductoMapper {

    ProductoRespuestaDTO toDto(Producto producto);

    Producto toEntity(ProductoRespuestaDTO dto);

    List<ProductoRespuestaDTO> toDtoList(List<Producto> productos);

    List<Producto> toEntityList(List<ProductoRespuestaDTO> dtoList);
}
