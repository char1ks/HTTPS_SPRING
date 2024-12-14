package com.example.SpringRedisJWT_demo7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringRedisJwtDemo7Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringRedisJwtDemo7Application.class, args);
	}

}
