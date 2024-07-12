package com.viaje.viaje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;



@SpringBootApplication
@PropertySource("file:.env")
public class ViajeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ViajeApplication.class, args);
	}

}



