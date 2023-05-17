package pebite.Ponitor_BE.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080", "http://43.200.29.57:8080",
                        "http://43.200.29.57:3000",
                        "http://localhost:3000"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}