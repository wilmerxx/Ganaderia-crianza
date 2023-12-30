package com.grupo1.ganaderiagrupo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class GanaderiaGrupo1Application {

    public static void main(String[] args) {
        SpringApplication.run(GanaderiaGrupo1Application.class, args);
    }
}
