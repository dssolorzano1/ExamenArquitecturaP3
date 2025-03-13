package com.facturar.producto.repositorio;

import com.facturar.producto.modelo.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductoRepository extends MongoRepository<Producto, String> {
}
