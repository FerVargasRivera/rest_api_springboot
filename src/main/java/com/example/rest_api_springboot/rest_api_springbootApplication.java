package com.example.rest_api_springboot;

import com.example.rest_api_springboot.model.Estudiante;
import com.example.rest_api_springboot.model.Grupo;
import com.example.rest_api_springboot.model.Materia;
import com.example.rest_api_springboot.model.Profesor;
import com.example.rest_api_springboot.persistence.EstudianteRepository;
import com.example.rest_api_springboot.persistence.GrupoRepository;
import com.example.rest_api_springboot.persistence.MateriaRepository;
import com.example.rest_api_springboot.persistence.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class rest_api_springbootApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(rest_api_springbootApplication.class, args);
	}

	// @Autowired inyectamos las dependencias de otros componentes
	@Autowired
	MateriaRepository materiaRepository;
	@Autowired
	EstudianteRepository estudianteRepository;
	@Autowired
	GrupoRepository grupoRepository;
	@Autowired
	ProfesorRepository profesorRepository;

	// Acceso a la consola de H2: http://localhost:8080/h2-console

	//Creamos el metodo run para hacer las inserciones a las tablas
	@Override
	public void run(String... args) throws Exception{

	 Materia materia1 = new Materia(null, "Matematicas",1);
	 materiaRepository.save(materia1);
	 Materia materia2 = new Materia(null, "Geografia",1);
	 materiaRepository.save(materia2);
	 Materia materia3 = new Materia(null, "Historia",1);
	 materiaRepository.save(materia3);

	 Grupo group1 = new Grupo(null,"IT-001",1,materia1);
	 grupoRepository.save(group1);
	 Grupo group2 = new Grupo(null,"IT-002",1,materia1);
	 grupoRepository.save(group2);
	 Grupo group3 = new Grupo(null,"IT-003",1, materia2);
	 grupoRepository.save(group3);
	 Grupo group4 = new Grupo(null,"IT-004",1,materia2);
	 grupoRepository.save(group4);
	 Grupo group5 = new Grupo(null,"IT-005",1,materia3);
	 grupoRepository.save(group5);

	 profesorRepository.save(new Profesor(null,"Luis","Perez","Campos",28,"luis_perez@gmail.com",1,group1));
	 profesorRepository.save(new Profesor(null,"Omar","Rangel","Rios",29,"omar_rangel@gmail.com",1,group2));
	 profesorRepository.save(new Profesor(null,"Raul","Contreras","Rivera",30,"raul_conteras@gmail.com",1,group3));
	 profesorRepository.save(new Profesor(null,"Esteban Roberto","Vargas","Rivera",45,"esteban_vargas@gmail.com",1,group4));
	 profesorRepository.save(new Profesor(null,"Francisco Javier","Ortega","Sierra",34,"francisco_ortega@gmail.com",1,group5));

	 estudianteRepository.save(new Estudiante(null,"Andres","Rocha","Mendez",18,"andy_rocha@gmail.com",1,group1));
	 estudianteRepository.save(new Estudiante(null,"Jose Julian","Ortega","Hernandez",17,"jose_ortega@gmail.com",1,group1));
	 estudianteRepository.save(new Estudiante(null,"Mario Alberto","Salas","Ornelas",20,"mario_salas@gmail.com",1,group2));
	 estudianteRepository.save(new Estudiante(null,"Nicolas","Almada","Gutierrez",23,"nico_almada@gmail.com",1,group2));
	 estudianteRepository.save(new Estudiante(null,"Julio","Zapata","Sierra",25,"julio_zapata@gmail.com",1,group3));
	 estudianteRepository.save(new Estudiante(null,"Alfredo","Silva","Mendez",27,"alfredo_silva@gmail.com",1,group3));
	 estudianteRepository.save(new Estudiante(null,"Juan Manuel","Luna","Fernandez",28,"jmanuel_luna@gmail.com",1,group4));
	 estudianteRepository.save(new Estudiante(null,"Sergio","Ramos","Andrade",29,"sergio_ramos@gmail.com",1,group4));
	 estudianteRepository.save(new Estudiante(null,"Samuel","Andrade","Ruiz",30,"samuel_andrade@gmail.com",1,group5));
	 estudianteRepository.save(new Estudiante(null,"David Luis","Estefan","Coria",30,"david_estefan@gmail.com",1,group5));
	 estudianteRepository.save(new Estudiante(null,"Eduardo","Videgaray","Perez",20,"eduardo_videgaray@gmail.com",1,group1));
	 estudianteRepository.save(new Estudiante(null,"Iker","Casillas","Sotelo",23,"iker_casillas@gmail.com",1,group2));
	}

	// Acceso a la interfaz de usuario de SWAGGER: http://localhost:8080/swagger-ui/index.html#/
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.rest_api_springboot.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Shop APIs")
				.description("Spring Boot Shop APIs Documentation")
				.version("1.0.0")
				.build();
	}

}
