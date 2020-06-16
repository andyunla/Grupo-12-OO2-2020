package com.sistema.application.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sistema.application.services.IEmpleadoService;
import com.sistema.application.services.IPedidoStockService;
import com.sistema.application.services.IProductoService;
import com.sistema.application.repositories.IPedidoStockRepository;
import com.sistema.application.repositories.IUserRepository;
import com.sistema.application.converters.EmpleadoConverter;
import com.sistema.application.converters.PedidoStockConverter;
import com.sistema.application.converters.UserConverter;
import com.sistema.application.entities.PedidoStock;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.PedidoStockModel;
import com.sistema.application.models.ProductoModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service("pedidoStockService")
public class PedidoStockService implements IPedidoStockService{

	//Atributos
		@Autowired
		@Qualifier("pedidoStockRepository")
		private IPedidoStockRepository pedidoStockRepository;
		
		@Autowired
		@Qualifier("pedidoStockConverter")
		private PedidoStockConverter pedidoStockConverter;
		@Autowired
		@Qualifier("empleadoConverter")
		private EmpleadoConverter empleadoConverter;
		@Autowired
		@Qualifier("userConverter")
		private UserConverter userConverter;
		@Autowired
		@Qualifier("userRepository")
		private IUserRepository userRepository;
		@Autowired
		@Qualifier("empleadoService")
		private IEmpleadoService empleadoService;
		@Autowired
		@Qualifier("productoService")
		private IProductoService productoService;
		//Métodos
		public PedidoStockModel findByIdPedidoStock(long idPedidoStock) {
			return pedidoStockConverter.entityToModel(pedidoStockRepository.findByIdPedidoStock(idPedidoStock) );
		}
		
		@Override
		public List<PedidoStock> getAll(){
			return pedidoStockRepository.findAll();
		}
		
		@Override
		public List<PedidoStockModel> getAllModel(){
			List<PedidoStockModel> pedidoStock = new ArrayList<PedidoStockModel>();
			for(PedidoStock p: pedidoStockRepository.findAll() ) {
				pedidoStock.add(pedidoStockConverter.entityToModel(p) );
			}
			return pedidoStock;
		}
		
		@Override
		public PedidoStockModel insertOrUpdate(PedidoStockModel pedidoStockModel) {
			PedidoStock pedidoStock = pedidoStockRepository.save(pedidoStockConverter.modelToEntity(pedidoStockModel) );
			return pedidoStockConverter.entityToModel(pedidoStock);
		}
		
		@Override
		public boolean remove(long id) {
			try {
				pedidoStockRepository.deleteById(id);
				return true;
			}catch(Exception e) {
				return false;
			}
		}
		
		@Override
		public Set<PedidoStockModel> findByEmpleadoSolicitante(EmpleadoModel empleadoSolicitante) {
			Set<PedidoStockModel> pedidoStock = new HashSet<PedidoStockModel>();
			for(PedidoStock p: pedidoStockRepository.findByEmpleadoSolicitante(empleadoSolicitante.getId()) ) {
				pedidoStock.add(pedidoStockConverter.entityToModel(p));
			}
			
			return pedidoStock;
		}
		@Override
		public List<PedidoStockModel> findByEmpleadoSolicitanteNoFacturado(long idEmpleadoSolicitante){
			List<PedidoStockModel> pedidoStock = new ArrayList<PedidoStockModel>();
			for(PedidoStock p: pedidoStockRepository.findByEmpleadoSolicitanteNoFacturado(idEmpleadoSolicitante)){
				pedidoStock.add(pedidoStockConverter.entityToModel(p));
			}
			return pedidoStock;
		}
		@Override
		public List<PedidoStockModel> findByEmpleadoSolicitanteFacturado(long idEmpleadoSolicitante){
			List<PedidoStockModel> pedidoStock = new ArrayList<PedidoStockModel>();
			for(PedidoStock p: pedidoStockRepository.findByEmpleadoSolicitanteFacturado(idEmpleadoSolicitante)){
				pedidoStock.add(pedidoStockConverter.entityToModel(p));
			}
			return pedidoStock;
		}
		
		@Override
		public PedidoStockModel crearPedido(String userSolicitante, String userOferente, boolean aceptado, long idProducto, int cantidad) {
			ProductoModel producto = productoService.findByIdProducto(idProducto);
			com.sistema.application.entities.User solicitante = userRepository.findByUsernameAndFetchUserRolesEagerly(userSolicitante);
			com.sistema.application.entities.User oferente = userRepository.findByUsernameAndFetchUserRolesEagerly(userOferente);
			EmpleadoModel solicitanteModel = empleadoService.findByLegajo(solicitante.getEmpleado().getLegajo());
			EmpleadoModel oferenteModel = empleadoService.findByLegajo(oferente.getEmpleado().getLegajo());
			
			PedidoStockModel pedido = new PedidoStockModel(producto, cantidad, aceptado, solicitanteModel, oferenteModel, false);
			
			return insertOrUpdate(pedido);
		}
}
