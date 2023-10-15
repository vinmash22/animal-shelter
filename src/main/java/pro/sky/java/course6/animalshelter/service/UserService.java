package pro.sky.java.course6.animalshelter.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course6.animalshelter.entity.User;
import pro.sky.java.course6.animalshelter.repository.UserRepository;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(long id) {
        return userRepository.findUserById(id);
    }
    public User findUserByChatId(long chatId) {
        return userRepository.findUserByChatId(chatId);
    }
}
