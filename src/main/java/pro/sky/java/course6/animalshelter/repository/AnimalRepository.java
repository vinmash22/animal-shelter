package pro.sky.java.course6.animalshelter.repository;


import org.springframework.data.repository.CrudRepository;
import pro.sky.java.course6.animalshelter.entity.Animal;

public interface AnimalRepository extends CrudRepository<Animal, Long> {
}
