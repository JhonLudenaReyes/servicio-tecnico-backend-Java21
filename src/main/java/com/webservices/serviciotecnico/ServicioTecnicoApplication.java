package com.webservices.serviciotecnico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ServicioTecnicoApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ServicioTecnicoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ServicioTecnicoApplication.class, args);

		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//String hash = encoder.encode("veRpeZ895!*");
		//System.out.println(hash);
	}

}
