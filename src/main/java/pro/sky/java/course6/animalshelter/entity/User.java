package pro.sky.java.course6.animalshelter.entity;



import jakarta.persistence.*;

//import javax.persistence.*;
import java.util.Objects;

/** Данный класс используется для хранения данных об усыновителях
 *
 */
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "chatId")
    private Long chatId;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "phone")
    private String phone;
    @Column(name = "role")
    private String role;
    @Column(name = "animalId")
    private long animalId;

    public User() {
    }

    public User(long id, Long chatId, String name, int age, String phone, String role, long animalId) {
        this.id = id;
        this.chatId = chatId;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.role = role;
        this.animalId = animalId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(long animalId) {
        this.animalId = animalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && animalId == user.animalId && Objects.equals(chatId, user.chatId) && Objects.equals(name, user.name) && Objects.equals(phone, user.phone) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, name, age, phone, role, animalId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                ", animalId=" + animalId +
                '}';
    }
}