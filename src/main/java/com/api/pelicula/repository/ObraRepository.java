/*
 * ObraRepository.java 
 * 
 * Creado el 01 de septiembre de 2021
 * 
 */

package com.api.pelicula.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.pelicula.entity.Obra;

/**
 * 
 * @author RenzoChia
 *
 */

@Repository("obraRepository")
public interface ObraRepository extends JpaRepository<Obra, Long> {
	
	@Query(
			value = "SELECT * FROM obra o ORDER BY o.obra_anioproduccion DESC ", 
			nativeQuery = true
			)
	List<Obra> findAllObra();
	
	@Query(
			value = "SELECT * FROM obra o WHERE o.obra_tipo = ? ORDER BY o.obra_anioproduccion DESC ", 
			nativeQuery = true
			)
	List<Obra> findAllObraByTipo(String param);
	
}
