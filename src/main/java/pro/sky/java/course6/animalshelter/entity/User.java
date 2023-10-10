package pro.sky.java.course6.animalshelter.entity;



import jakarta.persistence.*;

import java.util.Objects;

/** Данный класс используется для хранения данных об усыновителях
 *
 */
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (name = "id_chat")
    private String id_chat;
    @Column (name = "name")
    private String name;
    @Column (name = "age")
    private int age;
    @Column (name = "phone")
    private String phone;
    @Column (name = "role")
    private String role;
    @Column (name = "animal_id")
    private long animal_id;

    public User(){

    }

    public User(long id, String id_chat, String name, int age, String phone, String role, long animal_id) {
        this.id = id;
        this.id_chat = id_chat;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.role = role;
        this.animal_id = animal_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && animal_id == user.animal_id && Objects.equals(id_chat, user.id_chat) && Objects.equals(name, user.name) && Objects.equals(phone, user.phone) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, id_chat, name, age, phone, role, animal_id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", id_chat='" + id_chat + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                ", animal_id=" + animal_id +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getId_chat() {
        return id_chat;
    }

    public void setId_chat(String id_chat) {
        this.id_chat = id_chat;
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

    public long getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(long animal_id) {
        this.animal_id = animal_id;
    }
}
