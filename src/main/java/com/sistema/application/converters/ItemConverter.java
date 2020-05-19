package com.sistema.application.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.models.ItemModel;
import com.sistema.application.entities.Item;


@Component("itemConverter")
public class ItemConverter {
	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;
	@Autowired
	@Qualifier("changoConverter")
	private ChangoConverter changoConverter;
	
	//De entidad a modelo long idItem, int cantidad, ProductoModel productoModel, ChangoModel changoModel
	public ItemModel entityToModel(Item item) {
		return new ItemModel(item.getIdItem(), item.getCantidad(),productoConverter.entityToModel(item.getProducto()) , changoConverter.entityToModel(item.getChango())  );
	}
	
	//De modelo a entidad
	public Item modelToEntity(ItemModel itemModel) {
		return new Item(itemModel.getIdItem(), itemModel.getCantidad(), productoConverter.modelToEntity(itemModel.getProductoModel()), changoConverter.modelToEntity(itemModel.getChangoModel()));
	}
	
}
