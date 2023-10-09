package pro.sky.java.course6.animalshelter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("pro.sky.java.course6.animalshelter.repository")
public class AnimalShelterApplication {
	public static void main(String[] args) {
		SpringApplication.run(AnimalShelterApplication.class, args);
	}

}
