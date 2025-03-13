package com.facturar.producto.controlador;

import com.facturar.producto.dto.ProductoRespuestaDTO;
import com.facturar.producto.modelo.Producto;
import com.facturar.producto.servicio.ProductoService;
import com.facturar.producto.controlador.mapper.ProductoRespuestaMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoRespuestaMapper productoMapper;

    @GetMapping("/{codigo}")
    public ResponseEntity<ProductoRespuestaDTO> buscarPorCodigo(@PathVariable String codigo) {
        return productoService.buscarPorCodigo(codigo)
                .map(productoMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductoRespuestaDTO> crearProducto(@RequestBody ProductoRespuestaDTO dto) {
        Producto producto = productoMapper.toEntity(dto);
        Producto creado = productoService.crearProducto(producto);
        return ResponseEntity.ok(productoMapper.toDto(creado));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<ProductoRespuestaDTO> modificarProducto(@PathVariable String codigo,
                                                                  @RequestBody ProductoRespuestaDTO dto) {
        Producto producto = productoMapper.toEntity(dto);
        Producto actualizado = productoService.modificarProducto(codigo, producto);
        return ResponseEntity.ok(productoMapper.toDto(actualizado));
    }
}
