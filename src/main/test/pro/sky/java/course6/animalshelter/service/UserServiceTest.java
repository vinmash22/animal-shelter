package pro.sky.java.course6.animalshelter.service;

//import org.junit.jupiter.api.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import pro.sky.java.course6.animalshelter.entity.User;
import pro.sky.java.course6.animalshelter.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Captor
    private ArgumentCaptor<User> captor;

    @Mock
    private UserRepository userRepository;
    @MockBean
    AnimalService animalService;

    @InjectMocks
    private UserService userService;

    final Long id = 1L;
    final Long chatId = 23590972L;
    final String name = "Bob";
    final int age = 33;
    final String phone = "+7486131042";
    final String role = "user";
    final Long animal_id = 5l;

    User user = prepareUser();
    @Test
    public void createUserTest () throws Exception  {
        userService.createUser(user);
        Mockito.verify(userRepository).save(captor.capture());
        assertEquals(id, captor.getValue().getId());
        assertEquals(chatId, captor.getValue().getChatId());
        assertEquals(name, captor.getValue().getName());
        assertEquals(age,captor.getValue().getAge());
        assertEquals(phone, captor.getValue().getPhone());
        assertEquals(role, captor.getValue().getRole());
    }
    @Test
    public void findUserByIdTest () throws Exception  {
        when(userRepository.findUserById(any(Long.class))).thenReturn(user);
        User user1 = userService.findUserById(id);

        assertEquals(name, user1.getName());
        assertEquals(chatId, user1.getChatId());
        assertEquals(name, user1.getName());
        assertEquals(age,user1.getAge());
        assertEquals(phone, user1.getPhone());
        assertEquals(role, user1.getRole());
    }
    @Test
    public void findUserByChatId () throws Exception  {
        when(userRepository.findUserByChatId(any(Long.class))).thenReturn(user);
        User user1 = userService.findUserByChatId(chatId);

        assertEquals(name, user1.getName());
        assertEquals(chatId, user1.getChatId());
        assertEquals(name, user1.getName());
        assertEquals(age,user1.getAge());
        assertEquals(phone, user1.getPhone());
        assertEquals(role, user1.getRole());
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
