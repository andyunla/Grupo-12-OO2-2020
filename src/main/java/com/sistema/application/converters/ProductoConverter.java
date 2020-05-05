package com.sistema.application.converters;

import com.sistema.application.entities.Producto;
import com.sistema.application.models.ProductoModel;

import org.springframework.stereotype.Component;

@Component("productoConverter")
public class ProductoConverter {

     public ProductoModel entityToModel(Producto producto) {
          return new ProductoModel(producto.getIdProducto(), producto.getNombre(), producto.getDescripcion(),
                    producto.getPrecio(), producto.getTalle());
     }

     public Producto modelToEntity(ProductoModel producto) {
          return new Producto(producto.getIdProducto(), producto.getNombre(), producto.getDescripcion(),
                    producto.getPrecio(), producto.getTalle());
     }
}