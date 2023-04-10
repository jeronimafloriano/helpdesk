package com.jeronima.helpdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class HelpdeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	/*Se necessário declarar a URL de acesso e os métodos:
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:8080", "http://localhost:4200/login")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
			}
		};
	}*/
}
