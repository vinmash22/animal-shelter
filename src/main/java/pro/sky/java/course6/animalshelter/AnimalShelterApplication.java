package pro.sky.java.course6.animalshelter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
//@ComponentScan("pro.sky.java.course6.animalshelter.repository")
public class AnimalShelterApplication {
	public static void main(String[] args) {
		SpringApplication.run(AnimalShelterApplication.class, args);
	}

}
