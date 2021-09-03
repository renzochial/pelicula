/*
 * ObraModel.java 
 * 
 * Creado el 01 de septiembre de 2021
 * 
 */

package com.api.pelicula.model;

/**
 * 
 * @author RenzoChia
 *
 */

public class ObraModel {
	
	private Long idObra;

	private String titulo;

	private String descripcion;

	private String urlImagen;

	private String tipo;
	
	private Integer anioProduccion;

	public ObraModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdObra() {
		return idObra;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public String getTipo() {
		return tipo;
	}

	public void setIdObra(Long idObra) {
		this.idObra = idObra;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setUrlImagen(String urlimagen) {
		this.urlImagen = urlimagen;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getAnioProduccion() {
		return anioProduccion;
	}

	public void setAnioProduccion(Integer anioproduccion) {
		this.anioProduccion = anioproduccion;
	}
	
}
