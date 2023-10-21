package pro.sky.java.course6.animalshelter.service;
import org.springframework.stereotype.Service;
import pro.sky.java.course6.animalshelter.entity.Animal;
import pro.sky.java.course6.animalshelter.entity.User;
import pro.sky.java.course6.animalshelter.repository.AnimalRepository;
import pro.sky.java.course6.animalshelter.repository.UserRepository;

import java.util.Date;

@Service
public class AnimalService {


    private final AnimalRepository animalRepository;
    private final UserRepository userRepository;

    public AnimalService(AnimalRepository animalRepository, UserRepository userRepository) {
        this.animalRepository = animalRepository;
        this.userRepository = userRepository;
    }

    public Animal createAnimal (Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal findAnimalById(long id) {
        return animalRepository.findAnimalById(id);
    }

    public Animal takeAnAnimal(long idUser, long idAnimal){
        Animal animal = animalRepository.findAnimalById(idAnimal);
        User user = userRepository.findUserById(idUser);
        animal.setUser(user);
        Date date = new Date();
        animal.setDate(date);
        return animalRepository.save(animal);
    }


}





