package com.controlvacunacion.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class BackEndApplication {

	public static void main(String[] args) {
		setupTimeZone();
		SpringApplication.run(BackEndApplication.class, args);
	}

	private static void setupTimeZone() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

}
