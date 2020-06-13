package com.sistema.application.services;

import com.sistema.application.entities.Lote;
import com.sistema.application.models.LoteModel;
import com.sistema.application.models.ProductoModel;
import com.sistema.application.models.LocalModel;


import java.util.List;
import java.util.Set;

public interface ILoteService {
	
	public LoteModel findByIdLote(long idLote);
	
	public List<Lote> getAll();

	public List <LoteModel> getAllModel();

	public LoteModel insertOrUpdate(LoteModel loteModel);
	
	public boolean remove(long id);
	
	public List<LoteModel> findByLoteProductoActivo(long idProducto, long idLocal);
	
	public Set<LoteModel> findByLoteProductoBaja(long idProducto, long idLocal);
	
	public List<LoteModel> findByLocalProductoAndActivo(long idLocal, long idProducto, boolean soloActivos);

	public List<LoteModel> findByLoteProductoNoNuevo(long idProducto, long idLocal);

	public int calcularStock(ProductoModel Producto, LocalModel local);

	public boolean verificarStock(ProductoModel Producto, LocalModel local, int cantidad);
	
	public boolean consumirStock(long idLocal, long idProducto, int cantidad);
	
	public boolean devolverStock(long idLocal, long idProducto, int cantidad);

}
