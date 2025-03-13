package com.facturar.facturacion.modelo;

import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@ToString
@Document(collection = "FACTURAS")
public class Factura {

    @MongoId
    private String codFactura;

    private String tipoIdentificacion;
    private String identificacion;
    private String nombre;
    private LocalDateTime fecha;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal subtotal;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal iva;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal total;

    public Factura(String tipoIdentificacion, String identificacion, String nombre,
                   LocalDateTime fecha, BigDecimal subtotal, BigDecimal iva, BigDecimal total) {
        this.tipoIdentificacion = tipoIdentificacion;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
    }
}
