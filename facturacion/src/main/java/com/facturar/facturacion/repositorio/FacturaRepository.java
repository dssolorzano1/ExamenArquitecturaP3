package com.facturar.facturacion.repositorio;

import com.facturar.facturacion.modelo.Factura;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends MongoRepository<Factura, String> {
    
}
