package pro.sky.java.course6.animalshelter.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import pro.sky.java.course6.animalshelter.entity.Animal;
import pro.sky.java.course6.animalshelter.entity.User;
import pro.sky.java.course6.animalshelter.repository.AnimalRepository;
import pro.sky.java.course6.animalshelter.repository.UserRepository;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnimalServiceTest {
    @Captor
    private ArgumentCaptor<Animal> captor;

    @Mock
    private AnimalRepository animalRepository;
    @MockBean
    UserService userService;

    @InjectMocks
    private AnimalService animalService;

    final Long id = 1L;
    final String type = "dog";
    final String breed = "bulldog";
    final String sex = "male";
    final int age = 5;
    final String color = "black";
    final String name = "Шарик";
    Date date = new Date();
    String reportText = "It's ok";

    Animal animal = prepareAnimal();
    @Test
    public void createAnimalTest () throws Exception  {
        animalService.createAnimal(animal);
        Mockito.verify(animalRepository).save(captor.capture());
        assertEquals(id, captor.getValue().getId());
        assertEquals(type, captor.getValue().getType());
        assertEquals(breed, captor.getValue().getBreed());
        assertEquals(sex, captor.getValue().getSex());
        assertEquals(name, captor.getValue().getName());
        assertEquals(age,captor.getValue().getAge());
        assertEquals(color, captor.getValue().getColor());
    }

    @Test
    public void findUserByIdTest () throws Exception  {
        when(animalRepository.findAnimalById(any(Long.class))).thenReturn(animal);
        Animal animal1 = animalService.findAnimalById(id);
        assertEquals(id, animal1.getId());
        assertEquals(type, animal1.getType());
        assertEquals(breed, animal1.getBreed());
        assertEquals(sex, animal1.getSex());
        assertEquals(name, animal1.getName());
        assertEquals(age,animal1.getAge());
        assertEquals(color, animal1.getColor());
    }
    private Animal prepareAnimal() {
        Animal animal = new Animal();
        animal.setId(id);
        animal.setType(type);
        animal.setBreed(breed);
        animal.setSex(sex);
        animal.setAge(age);
        animal.setColor(color);
        animal.setName(name);
        animal.setDate(date);
        animal.setReportText(reportText);
//        animal.setUser(user);
        return animal;
    }
}

