package com.sistema.application.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.Item;

@Repository("itemRepository")
public interface IItemRepository extends JpaRepository<Item, Serializable>{
	public abstract Item findByIdItem(long idItem);

	@Query("FROM Item WHERE id_chango = (:idChango) AND id_producto = (:idProducto)")
	public abstract Item findByChangoAndProducto(long idChango, long idProducto);

	@Query("FROM Item WHERE id_chango = (:idChango)")
	public abstract List<Item> findByChango(long idChango);
}
