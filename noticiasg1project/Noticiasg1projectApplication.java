package br.com.ifs.noticiasg1project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Noticiasg1projectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Noticiasg1projectApplication.class, args);
	}

}
