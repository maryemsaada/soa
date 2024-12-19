package com.projet.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.projet.demo")
public class BijouxApplication {

	public static void main(String[] args) {
		SpringApplication.run(BijouxApplication.class, args);
	}
}
