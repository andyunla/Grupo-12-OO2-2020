package com.sistema.application.services;

import com.sistema.application.entities.Item;
import com.sistema.application.models.ItemModel;

import java.util.List;

public interface IItemService {

	public ItemModel findByIdItem(long idItem);

	public ItemModel findByChangoAndProducto(long idChango, long idProducto);

	public List<ItemModel> findByChango(long idChango);
	
	public List<Item> getAll();
	
	public List<ItemModel> getAllModel();
	
	public ItemModel insertOrUpdate(ItemModel itemModel);
	
	public boolean remove(long id);
}
