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

    public User createUser (User user) {
               return userRepository.save(user);
    }
 //   public User findUserByChatId (long id_chat) {
  //      return  userRepository.findUserByChatId(id_chat);
  //  }
}

