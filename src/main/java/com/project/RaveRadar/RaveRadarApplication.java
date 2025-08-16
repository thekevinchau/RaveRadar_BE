package com.project.RaveRadar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class RaveRadarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RaveRadarApplication.class, args);
	}

}
