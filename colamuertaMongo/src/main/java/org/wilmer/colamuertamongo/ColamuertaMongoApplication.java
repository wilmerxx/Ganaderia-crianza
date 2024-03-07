package org.wilmer.colamuertamongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class ColamuertaMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ColamuertaMongoApplication.class, args);
	}

}
