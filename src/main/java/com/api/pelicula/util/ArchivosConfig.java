/*
 * ArchivosConfig.java 
 * 
 * Creado el 01 de septiembre de 2021
 * 
 */

package com.api.pelicula.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @author RenzoChia
 *
 */

@ConfigurationProperties(prefix = "archivos", ignoreInvalidFields = true)
public class ArchivosConfig {

	private String rutaImagen;

	public String getRutaImagen() {
		return rutaImagen;
	}
	
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
}
