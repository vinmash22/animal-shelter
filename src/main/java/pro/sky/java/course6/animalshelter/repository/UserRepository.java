package pro.sky.java.course6.animalshelter.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.sky.java.course6.animalshelter.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByChatId(long chatId);


}
