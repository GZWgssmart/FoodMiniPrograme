package com.gzzy.foodManProgram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class FoodManProgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodManProgramApplication.class, args);
	}
}
