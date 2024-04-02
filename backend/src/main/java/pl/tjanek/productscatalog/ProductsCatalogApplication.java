package pl.tjanek.productscatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ProductsCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsCatalogApplication.class, args);
	}

	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
						.allowedOrigins("http://127.0.0.1:5173", "http://localhost:5173")
						.allowedMethods("GET", "POST", "DELETE", "OPTIONS", "ORIGIN")
						.allowedHeaders("Origin", "Content-Type", "Accept", "Access-Control-Allow-Origin");
			}
		};
	}
}
