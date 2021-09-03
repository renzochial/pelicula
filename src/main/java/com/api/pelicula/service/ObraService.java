/*
 * ObraService.java 
 * 
 * Creado el 01 de septiembre de 2021
 * 
 */

package com.api.pelicula.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.api.pelicula.model.ObraModel;

/**
 * 
 * @author RenzoChia
 *
 */

public interface ObraService {
	
	public abstract List<ObraModel> getAll(String param);
	
	public abstract ObraModel getObraById(long idObra);
	
	public abstract ObraModel addObra( ObraModel obraModelRequest, MultipartFile[] uploadFiles )  throws IOException, URISyntaxException ;
	
	public abstract ObraModel updateObra( ObraModel obraModelRequest, MultipartFile[] uploadFiles )  throws IOException, URISyntaxException ;
	
	public abstract void removeObra(long idObra, String nombreArchivo);

}
