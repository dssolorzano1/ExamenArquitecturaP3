package com.facturar.producto.modelo;

import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Data
@ToString
@Document(collection = "PRODUCTOS")
public class Producto {

    @MongoId
    private String codProducto;

    private String nombre;

    @Field(targetType = FieldType.INT32)
    private Integer existencia;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal precio;
}
