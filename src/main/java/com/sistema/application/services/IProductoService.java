package com.sistema.application.services;

import java.util.List;

import com.sistema.application.models.ProductoModel;
import com.sistema.application.entities.Producto;

public interface IProductoService {

	public ProductoModel findByIdProducto(long idProducto);
	
	public List<Producto> getAll();
	
    public List<ProductoModel> getAllModel();
    
    public ProductoModel insertOrUpdate(ProductoModel productoModel);
    
	public boolean remove(long idProducto);
	
	public ProductoModel findByNombre(String nombre);
}