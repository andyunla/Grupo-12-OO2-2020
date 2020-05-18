package com.sistema.application.services.implementations;

import java.util.ArrayList;
import java.util.List;

import com.sistema.application.converters.LocalConverter;
import com.sistema.application.entities.Local;
import com.sistema.application.models.LocalModel;
import com.sistema.application.repositories.ILocalRepository;
import com.sistema.application.services.ILocalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("localService")
public class LocalService implements ILocalService {

     @Autowired
	@Qualifier("localRepository")
	private ILocalRepository localRepository;
	
	@Autowired
	@Qualifier("localConverter")
     private LocalConverter localConverter;
     
     @Override
     public List<LocalModel> getAll() {
          List <LocalModel> localesModelos = new ArrayList<LocalModel>();
          for(Local entidadLocal: localRepository.findAll()){
               LocalModel modeloLocal = localConverter.entityToModel(entidadLocal);
               localesModelos.add(modeloLocal);
          }
          return localesModelos;
     }

     @Override
     public LocalModel findByIdLocal(long idLocal) {
          return localConverter.entityToModel(localRepository.findByIdLocal(idLocal));
     }

     @Override
     public LocalModel insertOrUpdate(LocalModel localModel) {
          Local localEntidad = localConverter.modelToEntity(localModel);
          Local localGuardado = localRepository.save(localEntidad);
          return localConverter.entityToModel(localGuardado);
     }

     @Override
     public boolean remove(long id) {
          try{
               localRepository.deleteById(id);
               return true;
          }
          catch(Exception e){
               return false;
          }
     }
     
}
