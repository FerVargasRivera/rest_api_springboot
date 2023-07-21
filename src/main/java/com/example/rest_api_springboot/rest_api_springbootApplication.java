package com.example.rest_api_springboot;

import com.example.rest_api_springboot.model.Materia;
import com.example.rest_api_springboot.persistence.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class rest_api_springbootApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(rest_api_springbootApplication.class, args);
	}

	@Autowired
	MateriaRepository materiaRepository;

	@Override
	public void run(String... args) throws Exception{
	 materiaRepository.save(new Materia(null, "Matematicas"));
	 materiaRepository.save(new Materia(null, "Geografia"));
	 materiaRepository.save(new Materia(null, "Historia"));
	 materiaRepository.save(new Materia(null, "Formacion Civica y Etica"));
	 materiaRepository.save(new Materia(null, "Espanol"));
	}
}
