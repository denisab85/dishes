package com.denisab85.dishes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.denisab85.dishes.repositories.naturalid.NaturalRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = NaturalRepositoryImpl.class)
public class DishesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DishesApplication.class, args);
	}

}
