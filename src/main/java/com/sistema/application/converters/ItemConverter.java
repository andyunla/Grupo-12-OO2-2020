package com.sistema.application.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.models.ItemModel;

import java.util.HashSet;
import java.util.Set;

import com.sistema.application.entities.Item;

@Component("itemConverter")
public class ItemConverter {
	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;
	@Autowired
	@Qualifier("changoConverter")
	private ChangoConverter changoConverter;

	// Entites to models
	// *******************************************************
	public ItemModel entityToModel(Item item) {
		return new ItemModel(item.getIdItem(), item.getCantidad(),
				productoConverter.entityToModel(item.getProducto()),
				changoConverter.entityToModelWithoutItems(item.getChango()));
	}	

	// Convierte una set de Item a un set de ItemModel
	public Set<ItemModel> entitiesToModels(Set<Item> itemsEntities) {
		Set<ItemModel> itemsModels = new HashSet<ItemModel>();
		for (Item item : itemsEntities) {
			itemsModels.add(entityToModel(item));
		}
		return itemsModels;
	}

	// Models to entities
	// *******************************************************
	public Item modelToEntity(ItemModel itemModel) {
		return new Item(itemModel.getIdItem(), itemModel.getCantidad(),
				productoConverter.modelToEntity(itemModel.getProductoModel()),
				changoConverter.modelToEntityWithoutItems(itemModel.getChangoModel()));
	}

	// Convierte una set de itemModel a un set de Item (Entidad)
	public Set<Item> modelsToEntity(Set<ItemModel> itemsModels) {
		Set<Item> itemsEntities = new HashSet<Item>();
		for (ItemModel itemModel : itemsModels) {
			itemsEntities.add(modelToEntity(itemModel));
		}
		return itemsEntities;
	}
}
