package com.facturar.producto.servicio;

import com.facturar.producto.modelo.Producto;
import com.facturar.producto.repositorio.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Optional<Producto> buscarPorCodigo(String codProducto) {
        return productoRepository.findById(codProducto);
    }

    public Producto crearProducto(Producto producto) {
        if (productoRepository.existsById(producto.getCodProducto())) {
            throw new RuntimeException("Producto ya existe con código: " + producto.getCodProducto());
        }
        return productoRepository.save(producto);
    }

    public Producto modificarProducto(String codProducto, Producto producto) {
        Producto existente = productoRepository.findById(codProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con código: " + codProducto));

        existente.setNombre(producto.getNombre());
        existente.setExistencia(producto.getExistencia());
        existente.setPrecio(producto.getPrecio());

        return productoRepository.save(existente);
    }
}
