/*
 * ObraController.java 
 * 
 * Creado el 01 de septiembre de 2021
 * 
 */

package com.api.pelicula.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.pelicula.model.ObraModel;
import com.api.pelicula.service.ObraService;
import com.api.pelicula.util.ArchivosConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * 
 * @author RenzoChia
 *
 */

@CrossOrigin(origins = "*")
@RestController
public class ObraController {
	
	public static Log LOG = LogFactory.getLog(ObraController.class);
	public static Gson gson = new Gson();
	
	@Autowired
	@Qualifier("obraService")
	private ObraService obraService;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private ArchivosConfig archivosConfig;
	
	@RequestMapping(value = "/obra", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<HashMap<String, String>> addObra(
			@RequestParam("obraModelRequest") String obraModelRequest,
			@RequestParam("imagen") MultipartFile[] uploadFiles)  throws IOException, URISyntaxException  {
		
		LOG.info(" addObra ---data : " +gson.toJson(obraModelRequest));
		
		HashMap<String, String> msg = new HashMap<>();
		ObraModel obraModelReturn = null;
		
		try {
			obraModelReturn = obraService.addObra(this.convertStringToModel(obraModelRequest), uploadFiles);
			if(obraModelReturn != null) {				
				msg.put("msg", "ok");
			}
			return new ResponseEntity<>(msg, HttpStatus.OK);
		} catch (HibernateException e) {
			LOG.error(" Error : " + e.getMessage());
			return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/obra", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<HashMap<String, String>> updateObra(
			@RequestParam("obraModelRequest") String obraModelRequest,
			@RequestParam("imagen") MultipartFile[] uploadFiles)  throws IOException, URISyntaxException  {
		
		HashMap<String, String> msg = new HashMap<>();
		ObraModel obraModelReturn = null;
		
		try {
			obraModelReturn = obraService.updateObra(this.convertStringToModel(obraModelRequest), uploadFiles);
			if(obraModelReturn != null) {				
				msg.put("msg", "ok");
			}
			return new ResponseEntity<>(msg, HttpStatus.OK);
		} catch (HibernateException e) {
			LOG.error(" Error : " + e.getMessage());
			return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/obra/{idObra}", method = RequestMethod.DELETE)
	@ResponseBody
	public int deleteObra(@PathVariable long idObra) {
		int tmp = 0;
		try {
			ObraModel obraModel = obraService.getObraById(idObra);

			if (obraModel != null) {
				obraService.removeObra(idObra, obraModel.getUrlImagen());
				tmp = 1;
			}
		} catch (HibernateException e) {
			LOG.info(" Error : " + e.getMessage());
		}
		return tmp;
	}
	
	@RequestMapping(value = "/obra/{param}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ObraModel>> getAll(@PathVariable String param) {
		
		List<ObraModel> list = null;
		try {
			list = obraService.getAll(param);
			return new ResponseEntity<>(list, HttpStatus.OK);

		} catch (HibernateException e) {
			LOG.error(" Error : " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private ObraModel convertStringToModel(String obraModelRequest) {
		ObjectMapper mapper = new ObjectMapper();
		ObraModel obraModel = new ObraModel();

		try {
			obraModel = mapper.readValue(obraModelRequest, ObraModel.class);
		} catch (Exception e) {
			LOG.info(" Error : " + e.getMessage());
		}
		return obraModel;
	}
	
	@RequestMapping(value = "/obra/verArchivo/{nombre}", method = RequestMethod.GET)
	public ResponseEntity<String> verArchivo(@PathVariable String nombre) {
		System.out.println(nombre);
		try {
			Path dir = Paths.get(archivosConfig.getRutaImagen());
			String filename = StringUtils.cleanPath(nombre);
			Path filePath = dir.resolve(filename);
			File file = new File(filePath.toUri());
			ByteArrayResource resource;
			
			resource = new ByteArrayResource(Files.readAllBytes(filePath));
			
			String encodedString = Base64.getEncoder().encodeToString(resource.getByteArray());

			return ResponseEntity.ok()
					.contentLength(file.length())
					.contentType(MediaType.parseMediaType(servletContext.getMimeType(filePath.toString())))
					.body(encodedString);
		} catch (IOException e) {
			LOG.info("Error: Achivo no encontrado " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}
