package com.facturar.producto.controlador;

import com.facturar.producto.modelo.Producto;
import com.facturar.producto.servicio.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/{codigo}")
    public ResponseEntity<Producto> buscarPorCodigo(@PathVariable("codigo") String codigo) {
        return productoService.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        try {
            Producto creado = productoService.crearProducto(producto);
            return ResponseEntity.ok(creado);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Producto> modificarProducto(@PathVariable("codigo") String codigo,
                                                      @RequestBody Producto producto) {
        try {
            Producto actualizado = productoService.modificarProducto(codigo, producto);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
