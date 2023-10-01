package pro.sky.java.course6.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.java.course6.animalshelter.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
