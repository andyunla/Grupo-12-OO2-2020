package com.sistema.application.services.implementations;

import java.util.ArrayList;
import java.util.List;

import com.sistema.application.converters.ProductoConverter;
import com.sistema.application.entities.Producto;
import com.sistema.application.models.ProductoModel;
import com.sistema.application.repositories.IProductoRepository;
import com.sistema.application.services.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("productoService")
public class ProductoService implements IProductoService {

     @Autowired
     @Qualifier("productoRepository")
     private IProductoRepository productoRepository;

     @Autowired
     @Qualifier("productoConverter")
     private ProductoConverter productoConverter;

     @Override
     public List<ProductoModel> getAll() {
          List<ProductoModel> productos = new ArrayList<ProductoModel>();
          for (Producto entidadProducto : productoRepository.findAll()) {
               productos.add(productoConverter.entityToModel(entidadProducto));
          }
          return productos;
     }

     @Override
     public ProductoModel insertOrUpdate(ProductoModel productoModel) {
          Producto productoEntidad = productoConverter.modelToEntity(productoModel);
          Producto productoGuardado = productoRepository.save(productoEntidad);
          return productoConverter.entityToModel(productoGuardado);
     }

     @Override
     public boolean remove(long idProducto) {
          try {
               productoRepository.deleteById(idProducto);
               return true;
          } catch (Exception e) {
               return false;
          }
     }
}