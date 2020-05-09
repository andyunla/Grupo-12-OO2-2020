package com.sistema.application.converters;

import org.springframework.stereotype.Component;

import com.sistema.application.models.ItemModel;
import com.sistema.application.entities.Item;

import com.sistema.application.converters.ProductoConverter;
import com.sistema.application.models.ProductoModel;
import com.sistema.application.repositories.IChangoRepository;
import com.sistema.application.repositories.IProductoRepository;

@Component("itemConverter")
public class ItemConverter {

	
	//De entidad a modelo
	public ItemModel entityToModel(Item item) {
		return new ItemModel(item.getIdItem(), item.getCantidad(), item.getProducto().getIdProducto() );
	}
	
	//De modelo a entidad
	public Item modelToEntity(ItemModel itemModel) {
		IProductoRepository iPR = null;
		Producto prod = iPR.findByIdProducto(itemModel.getIdProducto());
		IChangoRepository iCR = null;
		Chango chango = iCR.findByIdChango(itemModel.);
		return new Item(itemModel.getCantidad(), prod,  );
	}
	
}
