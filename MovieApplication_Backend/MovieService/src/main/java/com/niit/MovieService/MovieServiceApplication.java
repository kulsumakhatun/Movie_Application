package com.niit.MovieService;

import com.niit.MovieService.filter.JwtFilter;
import com.niit.MovieService.services.MovieService;
import com.niit.MovieService.services.MovieServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
@EnableEurekaClient
public class MovieServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(MovieServiceApplication.class, args);
	}
	//@Bean
//	FilterRegistrationBean jwtFilter(){
//		FilterRegistrationBean frb = new FilterRegistrationBean();
//		frb.setFilter(new JwtFilter());
//		frb.addUrlPatterns("/movie/api/v1/*");
//		return frb;
//	}
}
