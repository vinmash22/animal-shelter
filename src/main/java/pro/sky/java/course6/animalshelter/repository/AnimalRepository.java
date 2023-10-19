package pro.sky.java.course6.animalshelter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.sky.java.course6.animalshelter.entity.Animal;
import pro.sky.java.course6.animalshelter.entity.User;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Animal findAnimalById(long id);
}
