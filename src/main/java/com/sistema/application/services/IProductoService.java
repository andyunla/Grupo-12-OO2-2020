package com.sistema.application.services;

import java.util.List;

import com.sistema.application.models.ProductoModel;

public interface IProductoService {

	//public ProductoModel findById(long id);
	
    public List<ProductoModel> getAll();
    
    public ProductoModel insertOrUpdate(ProductoModel productoModel);
    
	public boolean remove(long idProducto);
}