package com.ibero.diana.library_proyect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LibraryProyectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryProyectApplication.class, args);
	}

}
