package pro.sky.java.course6.animalshelter.service;
import org.springframework.stereotype.Service;
import pro.sky.java.course6.animalshelter.entity.Animal;
import pro.sky.java.course6.animalshelter.entity.User;
import pro.sky.java.course6.animalshelter.repository.AnimalRepository;
import pro.sky.java.course6.animalshelter.repository.UserRepository;

@Service
public class AnimalService {


    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Animal createAnimal (Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal findAnimalById(long id) {
        return animalRepository.findAnimalById(id);
    }

}





