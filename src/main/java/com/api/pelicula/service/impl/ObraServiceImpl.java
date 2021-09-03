/*
 * ObraServiceImpl.java 
 * 
 * Creado el 01 de septiembre de 2021
 * 
 */

package com.api.pelicula.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.pelicula.converter.ObraConverter;
import com.api.pelicula.entity.Obra;
import com.api.pelicula.model.ObraModel;
import com.api.pelicula.repository.ObraRepository;
import com.api.pelicula.service.ObraService;
import com.api.pelicula.util.ArchivosConfig;
import com.api.pelicula.util.Utilidades;

/**
 * 
 * @author RenzoChia
 *
 */

@Service("obraService")
public class ObraServiceImpl implements ObraService{
	
	@Autowired
	@Qualifier("obraRepository")
	private ObraRepository obraRepository;
	
	@Autowired
	@Qualifier("obraConverter")
	private ObraConverter obraConverter;
	
	@Autowired
	private ArchivosConfig archivosConfig;

	@Override
	public List<ObraModel> getAll(String param) {
		
		List<Obra> listaReturn = null;
		
		if(!param.isEmpty() && (param.equalsIgnoreCase("1") || param.equalsIgnoreCase("2"))) {
			listaReturn = obraRepository.findAllObraByTipo(param);
		}else {
			listaReturn = obraRepository.findAllObra();
		}
		
		List<ObraModel> listaRequest = new ArrayList<ObraModel>();
		
		for (int i = 0; i < listaReturn.size(); i++) {
			Obra auditoria = listaReturn.get(i);
			listaRequest.add(obraConverter.entityToModel(auditoria));
		}
		
		return listaRequest;
	}
	
	@Override
	public ObraModel getObraById(long idObra) {
		Optional<Obra> obra = obraRepository.findById(idObra);
		return obraConverter.entityToModel(obra.get());
	}

	@Override
	public ObraModel addObra(ObraModel obraModelRequest,  MultipartFile[] uploadFiles) throws IOException, URISyntaxException {
		
		ObraModel obraModelReturn = null;

		if (uploadFiles != null) {
			Path rutaImagen = Paths.get(archivosConfig.getRutaImagen());
			String nombreImagen = "img" + System.currentTimeMillis();
			for (MultipartFile file : uploadFiles) {
				String name = nombreImagen + "." + Utilidades.getExtension(file.getOriginalFilename()).get();
				Utilidades.guardarArchivo(name, file, rutaImagen);
				obraModelRequest.setUrlImagen(name);
			}
		}

		if (obraModelRequest != null) {
			obraModelReturn = obraConverter.entityToModel(obraRepository.save(obraConverter.modelToEntity(obraModelRequest)));
		}

		return obraModelReturn;
	}
	
	@Override
	public ObraModel updateObra(ObraModel obraModelRequest,  MultipartFile[] uploadFiles) throws IOException, URISyntaxException {
		
		ObraModel obraModelReturn = null;
		
		 System.out.println("Este es el año rervice===>"+obraModelRequest.getAnioProduccion());
		 
		 System.out.println("Este es el archivosConfig.getRutaImagen()===>"+archivosConfig.getRutaImagen());
		 
		 if (uploadFiles != null) {
				Path rutaImagen = Paths.get(archivosConfig.getRutaImagen());
				System.out.println("Este es el rutaImagen update===>"+rutaImagen);
				
				String nombreImagen = "img" + System.currentTimeMillis();
				Utilidades.eliminarArchivo(rutaImagen, obraModelRequest.getUrlImagen());
				for (MultipartFile file : uploadFiles) {
					String name = nombreImagen + "." + Utilidades.getExtension(file.getOriginalFilename()).get();
					Utilidades.guardarArchivo(name, file, rutaImagen);
					obraModelRequest.setUrlImagen(name);
				}
			}
		
		if(obraModelRequest != null) {
			obraModelReturn = obraConverter.entityToModel(obraRepository.save(obraConverter.modelToEntity(obraModelRequest)));
		}
		
		return obraModelReturn;
	}

	@Override
	public void removeObra(long idObra, String nombreArchivo) {
		
		if (!nombreArchivo.isEmpty()) {
			Path rutaImagen = Paths.get(archivosConfig.getRutaImagen());
			Utilidades.eliminarArchivo(rutaImagen, nombreArchivo);
		}
		obraRepository.deleteById(idObra);
	}

}
