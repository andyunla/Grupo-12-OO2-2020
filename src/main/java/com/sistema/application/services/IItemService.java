package com.sistema.application.services;

import com.sistema.application.entities.Item;
import com.sistema.application.models.ItemModel;

import java.util.List;

public interface IItemService {

	//public ItemModel findById(long id);
	
	public List<Item> getAll();
	
	public ItemModel ingresarOActualizar(ItemModel itemModel);
	
	public boolean eliminar(long id);
}
