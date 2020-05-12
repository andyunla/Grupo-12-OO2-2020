package com.sistema.application.services;

import com.sistema.application.entities.Item;
import com.sistema.application.models.ItemModel;

import java.util.List;

public interface IItemService {

	public List<Item> getAll();
	
	public ItemModel insertOrUpdate(ItemModel itemModel);
	
	public boolean remove(long id);
}
