/*
 * ObraConverter.java 
 * 
 * Creado el 01 de septiembre de 2021
 * 
 */

package com.api.pelicula.converter;

import org.springframework.stereotype.Component;

import com.api.pelicula.entity.Obra;
import com.api.pelicula.model.ObraModel;

/**
 * 
 * @author RenzoChia
 *
 */

@Component("obraConverter")
public class ObraConverter {
	
	//Entity --> Model
    public ObraModel entityToModel(Obra obra){
        ObraModel obraModel = new ObraModel();
        obraModel.setIdObra(obra.getIdObra());
        obraModel.setTitulo(obra.getTitulo());
        obraModel.setDescripcion(obra.getDescripcion());
        obraModel.setUrlImagen(obra.getUrlImagen());
        obraModel.setTipo(obra.getTipo());
        obraModel.setAnioProduccion(obra.getAnioProduccion());

        return obraModel;
    }

    //Model --> Entity
    public Obra modelToEntity(ObraModel obraModel){
        Obra obra = new Obra();
        obra.setIdObra(obraModel.getIdObra());
        obra.setTitulo(obraModel.getTitulo());
        obra.setDescripcion(obraModel.getDescripcion());
        obra.setUrlImagen(obraModel.getUrlImagen());
        obra.setTipo(obraModel.getTipo());
        obra.setAnioProduccion(obraModel.getAnioProduccion());

        return obra;
    }

}
