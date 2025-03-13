package com.facturar.facturacion.servicio;

import com.facturar.facturacion.dto.FacturaCabeceraDTO;
import com.facturar.facturacion.dto.FacturaDetalleDTO;
import com.facturar.facturacion.modelo.Factura;
import com.facturar.facturacion.repositorio.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String PRODUCTO_SERVICE_URL = "http://localhost:8081/productos/{codigo}";

    public Factura crearFactura(FacturaCabeceraDTO cabecera) {
        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal iva = BigDecimal.ZERO;
        BigDecimal total = BigDecimal.ZERO;

        for (FacturaDetalleDTO detalle : cabecera.getDetalles()) {
            ProductoDTO producto = restTemplate.getForObject(PRODUCTO_SERVICE_URL, ProductoDTO.class, detalle.getCodProducto());

            if (producto == null) {
                throw new RuntimeException("Producto no encontrado: " + detalle.getCodProducto());
            }

            if (producto.getExistencia() < detalle.getCantidad()) {
                throw new RuntimeException("No hay suficiente stock para el producto: " + detalle.getCodProducto());
            }

            BigDecimal precioUnitario = producto.getPrecio();
            BigDecimal cantidad = BigDecimal.valueOf(detalle.getCantidad());

            BigDecimal subtotalDetalle = precioUnitario.multiply(cantidad);
            BigDecimal ivaDetalle = subtotalDetalle.multiply(BigDecimal.valueOf(0.12)); // 12% IVA
            BigDecimal totalDetalle = subtotalDetalle.add(ivaDetalle);

            subtotal = subtotal.add(subtotalDetalle);
            iva = iva.add(ivaDetalle);
            total = total.add(totalDetalle);

        }

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

    static class ProductoDTO {
        private String codProducto;
        private String nombre;
        private Integer existencia;
        private BigDecimal precio;

        public String getCodProducto() { return codProducto; }
        public void setCodProducto(String codProducto) { this.codProducto = codProducto; }
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public Integer getExistencia() { return existencia; }
        public void setExistencia(Integer existencia) { this.existencia = existencia; }
        public BigDecimal getPrecio() { return precio; }
        public void setPrecio(BigDecimal precio) { this.precio = precio; }
    }
}
