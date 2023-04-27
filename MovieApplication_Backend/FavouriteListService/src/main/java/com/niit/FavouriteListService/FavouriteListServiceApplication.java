package com.niit.FavouriteListService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FavouriteListServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavouriteListServiceApplication.class, args);
	}

}
