/*
 * Utilidades.java 
 * 
 * Creado el 01 de septiembre de 2021
 * 
 */
package com.api.pelicula.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component("utilidades")
public class Utilidades {
	
	public static void guardarArchivo(String name, MultipartFile file, Path ruta) throws IOException {
		String filename = StringUtils.cleanPath(name);

		try (InputStream inputStream = file.getInputStream()) {
			Files.copy(inputStream, ruta.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
		}
	}

	public static void eliminarArchivo(Path ruta, String name) {
		File dir = new File(ruta.toString());
		String[] cadena = name.split("\\.");
        File[] fileList = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.contains(cadena[0]);
            }
        });
        
        if(fileList != null) {
        	for(File file : fileList) {
                file.delete();
            }
        }
	}
	
	public static Optional<String> getExtension(String filename) {
		return Optional.ofNullable(filename).filter(f -> f.contains("."))
				.map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}

}
