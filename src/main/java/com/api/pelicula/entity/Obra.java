/*
 * Obra.java 
 * 
 * Creado el 01 de septiembre de 2021
 * 
 */

package com.api.pelicula.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author RenzoChia
 *
 */

@Entity(name = "Obra")
public class Obra implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "obra_id", nullable = false)
	private Long idObra;

	@Column(name = "obra_titulo", nullable = false)
	private String titulo;

	@Column(name = "obra_descripcion", nullable = true)
	private String descripcion;

	@Column(name = "obra_urlimagen", nullable = false)
	private String urlImagen;

	@Column(name = "obra_tipo", nullable = false)
	private String tipo;

	@Column(name = "obra_anioproduccion", nullable = false)
	private Integer anioProduccion;

	public Obra() {
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

	public Integer getAnioProduccion() {
		return anioProduccion;
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

	public void setAnioProduccion(Integer anioproduccion) {
		this.anioProduccion = anioproduccion;
	}

}
