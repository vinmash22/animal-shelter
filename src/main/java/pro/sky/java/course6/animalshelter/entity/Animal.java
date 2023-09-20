package pro.sky.java.course6.animalshelter.entity;

import java.util.Objects;

/** Данный класс используется для хранения данные о животных (собаках и кошках)
 *
 */
public class Animal {
    long id;
    String type;
    String color;
    String name;
    int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public Animal(long id, String type, String color, String name, int age) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return age == animal.age && Objects.equals(type, animal.type) && Objects.equals(color, animal.color) && Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, color, name, age);
    }
}
