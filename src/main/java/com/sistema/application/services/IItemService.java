package com.sistema.application.services;

import com.sistema.application.entities.Item;
import com.sistema.application.models.ItemModel;

import java.util.List;
import java.util.Set;

public interface IItemService {

	public ItemModel findByIdItem(long idItem);
	
	public List<Item> getAll();
	
	public List<ItemModel> getAllModel();
	
	public ItemModel insertOrUpdate(ItemModel itemModel);

	public Set<ItemModel> insertOrUpdateMany(Set<ItemModel> items);
	
	public boolean remove(long id);
}
