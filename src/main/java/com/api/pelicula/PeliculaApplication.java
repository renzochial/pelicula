package com.api.pelicula;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.api.pelicula.util.ArchivosConfig;

@SpringBootApplication
@ComponentScan(basePackages = "com.api.pelicula")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.api.pelicula")
@EnableConfigurationProperties({ArchivosConfig.class})
public class PeliculaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeliculaApplication.class, args);
	}

}
