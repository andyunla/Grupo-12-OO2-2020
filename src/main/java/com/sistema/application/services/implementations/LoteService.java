package com.sistema.application.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sistema.application.services.ILocalService;
import com.sistema.application.services.ILoteService;
import com.sistema.application.repositories.ILoteRepository;
import com.sistema.application.converters.LocalConverter;
import com.sistema.application.converters.LoteConverter;
import com.sistema.application.converters.ProductoConverter;
import com.sistema.application.entities.Lote;
import com.sistema.application.models.LocalModel;
import com.sistema.application.models.LoteModel;
import com.sistema.application.models.ProductoModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service("loteService")
public class LoteService implements ILoteService {

	// Atributos
	@Autowired
	@Qualifier("loteRepository")
	private ILoteRepository loteRepository;

	@Autowired
	@Qualifier("loteConverter")
	private LoteConverter loteConverter;

	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;

	@Autowired
	@Qualifier("localService")
	private ILocalService localService;

	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;

	// Métodos
	@Override
	public LoteModel findByIdLote(long idLote) {
		return loteConverter.entityToModel(loteRepository.findByIdLote(idLote));
	}

	@Override
	public List<Lote> getAll() {
		return loteRepository.findAll();
	}

	@Override
	public LoteModel insertOrUpdate(LoteModel loteModel) {
		Lote lote = loteRepository.save(loteConverter.modelToEntity(loteModel));
		return loteConverter.entityToModel(lote);
	}

	@Override
	public boolean remove(long id) {
		try {
			loteRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<LoteModel> findByLoteProductoActivo(long idProducto, long idLocal) {
		// creo un set list vacio
		List<LoteModel> lista = new ArrayList<LoteModel>();
		// recorro la lista de lotes activos del poducto en el local correspondiente
		for (Lote lo : loteRepository.findByLoteProductoActivo(idProducto, idLocal)) {
			// a cada lote lo convierto de entidad a model y lo agrego a la lista
			lista.add(loteConverter.entityToModel(lo));
		}
		return lista;
	}

	@Override
	public Set<LoteModel> findByLoteProductoBaja(long idProducto, long idLocal) {
		// creo un set list vacio
		Set<LoteModel> lista = new HashSet<LoteModel>();
		// recorro la lista de lotes inactivos del poducto en el local correspondiente
		for (Lote lo : loteRepository.findByLoteProductoNoNuevo(idProducto, idLocal)) {
			// a cada lote lo convierto de entidad a model y lo agrego a la lista
			lista.add(loteConverter.entityToModel(lo));
		}
		return lista;
	}

	@Override
	public List<LoteModel> findByLoteProductoNoNuevo(long idProducto, long idLocal) {
		List<LoteModel> lista = new ArrayList<LoteModel>();
		for (Lote lo : loteRepository.findByLoteProductoNoNuevo(idProducto, idLocal)) {
			lista.add(loteConverter.entityToModel(lo));
		}
		return lista;
	}

	@Override
	public List<LoteModel> getAllModel() {
		List<LoteModel> lotes = new ArrayList<LoteModel>();
		for (Lote l : loteRepository.findAll()) {
			lotes.add(loteConverter.entityToModel(l));
		}
		return lotes;
	}

	@Override
	public int calcularStock(ProductoModel producto, LocalModel local) {
		int total = 0;
		for (LoteModel lote : findByLoteProductoActivo(producto.getIdProducto(), local.getIdLocal())) {
			total += lote.getCantidadActual();
		}
		return total;
	}

	@Override
	public boolean verificarStock(ProductoModel producto, LocalModel local, int cantidad) {
		return (calcularStock(producto, local) >= cantidad);
	}

	@Override
	public boolean consumirStock(long idLocal, long idProducto, int cantidad) {
		List<LoteModel> lista = findByLoteProductoActivo(idProducto, idLocal);
		Iterator<LoteModel> itr = lista.iterator();
		LoteModel lo = null;
		// Consume stock sobre los lotes activos del producto hasta cumplir con la cantidad
		while (cantidad > 0 && itr.hasNext()) {
			lo = itr.next();
			if (lo.getCantidadActual() - cantidad <= 0) { // si la cantidad actual queda en 0 doy de baja el lote
				cantidad = cantidad - lo.getCantidadActual(); // se actualiza la cantidad a restar
				lo.setCantidadActual(0);
				insertOrUpdate(lo);
			} else {
				lo.setCantidadActual(lo.getCantidadActual() - cantidad);
				insertOrUpdate(lo);
				cantidad = 0; // seteo en cero para salir del bucle, ya no hay mas que restar
			}
		}
		return cantidad == 0; // Si se pudo consumir la cantidad recibida devuelve true
	}

	@Override
	public boolean devolverStock(long idLocal, long idProducto, int cantidad) {
		List<LoteModel> lista = findByLoteProductoNoNuevo(idProducto, idLocal);
		Iterator<LoteModel> itr = lista.iterator();
		LoteModel lo;
		while (cantidad > 0 && itr.hasNext()) {
			lo = itr.next();
			if (lo.getCantidadActual() + cantidad >= lo.getCantidadInicial()) {
				cantidad = lo.getCantidadInicial() - lo.getCantidadActual();
				lo.setCantidadActual(lo.getCantidadInicial());
				insertOrUpdate(lo);
			} else {
				lo.setCantidadActual(lo.getCantidadActual() + cantidad);
				insertOrUpdate(lo);
				cantidad = 0;
			}
		}
		return cantidad == 0; // Si se pudo devolver la cantidad recibida devuelve true
	}

	@Override
	public List<LoteModel> findByLocalOrderByFechaingresoDesc(long idLocal) {
		List<LoteModel> lotes = new ArrayList <LoteModel>();
		LocalModel local = localService.findByIdLocal(idLocal);
		for(Lote lote : loteRepository.findByLocalOrderByFechaIngresoDesc(localConverter.modelToEntity(local))){
			lotes.add(loteConverter.entityToModel(lote));
		}
		return lotes;
	}

	@Override
	public List<LoteModel> findByALocalProductoAndActivo(long idLocal, long idProducto, boolean soloActivos) {
		List<LoteModel> lotes = new ArrayList <LoteModel>();
		for(Lote lote: loteRepository.findByALocalProductoAndActivo(idLocal, idProducto, soloActivos)) {
			lotes.add( loteConverter.entityToModel(lote));
		}
		return lotes;
	}
}
