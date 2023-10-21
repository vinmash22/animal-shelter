package pro.sky.java.course6.animalshelter.controller;

import org.springframework.http.ResponseEntity;
import pro.sky.java.course6.animalshelter.entity.Animal;
import pro.sky.java.course6.animalshelter.entity.User;

import org.springframework.web.bind.annotation.*;
import pro.sky.java.course6.animalshelter.service.AnimalService;
import pro.sky.java.course6.animalshelter.service.UserService;

@RestController
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity createAnimal(@RequestBody Animal animal) {
        Animal createdAnimal = animalService.createAnimal(animal);
        return ResponseEntity.ok(createdAnimal);
    }

    @GetMapping("{id}")
    public ResponseEntity<Animal> findAnimalById(@PathVariable Long id) {
        Animal animal = animalService.findAnimalById(id);
        if (animal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(animal);
    }
    @GetMapping("{idUser}/{idAnimal}")
    public ResponseEntity<Animal> takeAnAnimal(@PathVariable Long idUser, Long idAnimal) {
        Animal animal = animalService.takeAnAnimal(idUser, idAnimal);
        if (animal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(animal);
    }

}