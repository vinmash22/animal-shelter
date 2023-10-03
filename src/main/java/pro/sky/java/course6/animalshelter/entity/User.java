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
    @Column (name = "name")
    private String name;
    @Column (name = "age")
    private int age;
    @Column (name = "contacts")
    private String contacts;
    @Column (name = "passport")
    private int passport;

    public User(int id, String name, int age, String contacts, int passport) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.contacts = contacts;
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", contacts='" + contacts + '\'' +
                ", passport=" + passport +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && passport == user.passport && Objects.equals(name, user.name) && Objects.equals(contacts, user.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, contacts, passport);
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
    public int getPassport() {
        return passport;
    }
    public void setPassport(int passport) {
        this.passport = passport;
    }
}
