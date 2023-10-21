package pro.sky.java.course6.animalshelter.controller;


import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.java.course6.animalshelter.entity.User;
import pro.sky.java.course6.animalshelter.repository.UserRepository;
import pro.sky.java.course6.animalshelter.service.AnimalService;
import pro.sky.java.course6.animalshelter.service.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UserControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc; // позволяет тестировать без запуска HTTP-сервера
    @MockBean
    UserRepository userRepository;
    @MockBean
    AnimalService animalService;
    @SpyBean
    UserService userService;


    @InjectMocks
    UserController userController;

    @Test
    public void saveUserTest() throws Exception {
        final Long id = 1L;
        final String id_chat = "23590972";
        final String name = "Bob";
        final int age = 33;
        final String phone = "+7486131042";
        final String role = "user";
        final Long animal_id = 5l;

        JSONObject userObject = new JSONObject();
        userObject.put("id", id);
        userObject.put("id_chat", id_chat);
        userObject.put("name", name);
        userObject.put("age", age);
        userObject.put("phone", phone);
        userObject.put("role", role);
        userObject.put("animal_id", animal_id);

        User user = new User();
        user.setId(id);
        user.setId_chat(id_chat);
        user.setName(name);
        user.setAge(age);
        user.setPhone(phone);
        user.setRole(role);
        user.setAnimal_id(animal_id);

        when(userRepository.save(any(User.class))).thenReturn(user);
//        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));


        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users") //send
                        .content(userObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)  // что будем отправлять
                        .accept(MediaType.APPLICATION_JSON))    // что будем получать
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.id_chat").value(id_chat))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.phone").value(phone))
                .andExpect(jsonPath("$.role").value(role))
                .andExpect(jsonPath("$.animal_id").value(animal_id));

/*        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/" + id) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.chatId").value(chatId))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.phone").value(phone))
                .andExpect(jsonPath("$.role").value(role))
                .andExpect(jsonPath("$.animalId").value(animalId));*/
    }

}