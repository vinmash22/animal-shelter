package pro.sky.java.course6.animalshelter.controller;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pro.sky.java.course6.animalshelter.entity.Animal;
import pro.sky.java.course6.animalshelter.entity.User;
import pro.sky.java.course6.animalshelter.repository.AnimalRepository;
import pro.sky.java.course6.animalshelter.repository.UserRepository;
import pro.sky.java.course6.animalshelter.service.AnimalService;
import pro.sky.java.course6.animalshelter.service.UserService;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class AnimalControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc; // позволяет тестировать без запуска HTTP-сервера
    @MockBean
    AnimalRepository animalRepository;
    @MockBean
    UserRepository userRepository;
    @MockBean
    UserService userService;

    @SpyBean
    AnimalService animalService;



    @InjectMocks
    AnimalController animalController;

    final Long id = 1L;
    final String type = "dog";
    final String breed = "bulldog";
    final String sex = "male";
    final int age = 5;
    final String color = "black";
    final String name = "Шарик";
    Date date = new Date();
    String reportText = "It's ok";

    String dateString = date.toString();
    User user = null;

    Animal animal = prepareAnimal();
    JSONObject animalObject = prepareAnimalObject();

    @Test
    public void saveAnimalTest() throws Exception {
        when(animalRepository.save(any(Animal.class))).thenReturn(animal);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/animals") //send
                        .content(animalObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)  // что будем отправлять
                        .accept(MediaType.APPLICATION_JSON))    // что будем получать
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.type").value(type))
                .andExpect(jsonPath("$.breed").value(breed))
                .andExpect(jsonPath("$.sex").value(sex))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.color").value(color))
                .andExpect(jsonPath("$.name").value(name))
//                .andExpect(jsonPath("$.date").value(date))
                .andExpect(jsonPath("$.reportText").value(reportText));
    }

    @Test
    public void findAnimalByIdTest () throws Exception {
        when(animalRepository.findAnimalById(any(Long.class))).thenReturn(animal);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/animals/" + id) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.type").value(type))
                .andExpect(jsonPath("$.breed").value(breed))
                .andExpect(jsonPath("$.sex").value(sex))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.color").value(color))
                .andExpect(jsonPath("$.name").value(name))
 //               .andExpect(jsonPath("$.date").value(date.toString()))
                .andExpect(jsonPath("$.reportText").value(reportText));
    }



    private JSONObject prepareAnimalObject() {
        JSONObject animalObject = new JSONObject();
        animalObject.put("id", id);
        animalObject.put("type",type);
        animalObject.put("breed", breed);
        animalObject.put("sex", sex);
        animalObject.put("age", age);
        animalObject.put("color", color);
        animalObject.put("name", name);
//        animalObject.put("date", date.toString());
        animalObject.put("reportText", reportText);
        return animalObject;
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
        animal.setUser(user);
        return animal;
    }
}
