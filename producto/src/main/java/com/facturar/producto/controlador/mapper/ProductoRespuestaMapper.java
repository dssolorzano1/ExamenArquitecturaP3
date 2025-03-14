package com.facturar.producto.controlador.mapper;

import com.facturar.producto.dto.ProductoRespuestaDTO;
import com.facturar.producto.modelo.Producto;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductoRespuestaMapper {

    @Mapping(target = "codProducto", source = "codProducto")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "existencia", source = "existencia")
    @Mapping(target = "precio", source = "precio")
    ProductoRespuestaDTO toDto(Producto producto);

    @Mapping(target = "codProducto", source = "codProducto")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "existencia", source = "existencia")
    @Mapping(target = "precio", source = "precio")
    Producto toEntity(ProductoRespuestaDTO dto);
}
