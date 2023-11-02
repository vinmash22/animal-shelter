package pro.sky.java.course6.animalshelter.controller;

//import jakarta.persistence.OneToMany;
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
        import pro.sky.java.course6.animalshelter.entity.Animal;
import pro.sky.java.course6.animalshelter.entity.User;
import pro.sky.java.course6.animalshelter.repository.UserRepository;

import java.util.Collection;

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

    final Long id = 1L;
    final Long chatId = 23590972L;
    final String name = "Bob";
    final int age = 33;
    final String phone = "+7486131042";
    final String role = "user";
    final Long animal_id = 5l;
    Collection<Animal> animals = null;

    User user = prepareUser();
    JSONObject userObject = prepareUserObject();

    @Test
    public void saveUserTest() throws Exception {
        when(userRepository.save(any(User.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users") //send
                        .content(userObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)  // что будем отправлять
                        .accept(MediaType.APPLICATION_JSON))    // что будем получать
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.chatId").value(chatId))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.phone").value(phone))
                .andExpect(jsonPath("$.role").value(role));
    }
    @Test
    public void findUserByIdTest () throws Exception {
        when(userRepository.findUserById(any(Long.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/" + id) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.chatId").value(chatId))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.phone").value(phone))
                .andExpect(jsonPath("$.role").value(role));
    }

    @Test
    public void findUserByChatIdTest () throws Exception {
        when(userRepository.findUserByChatId(any(Long.class))).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/chatId/" + id) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.chatId").value(chatId))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.phone").value(phone))
                .andExpect(jsonPath("$.role").value(role));
    }

    private JSONObject prepareUserObject() {
        JSONObject userObject = new JSONObject();
        userObject.put("id", id);
        userObject.put("chatId", chatId);
        userObject.put("name", name);
        userObject.put("age", age);
        userObject.put("phone", phone);
        userObject.put("role", role);
        return userObject;
    }
    private User prepareUser() {
        User user = new User();
        user.setId(id);
        user.setChatId(chatId);
        user.setName(name);
        user.setAge(age);
        user.setPhone(phone);
        user.setRole(role);
//        user.setAnimals(animals);
        return user;
    }
}