package pro.sky.java.course6.animalshelter.entity;

import javax.persistence.*;

import java.util.Date;
import java.util.Objects;

/** Данный класс используется для хранения данные о животных (собаках и кошках)
 *
 */

@Entity
@Table(name="animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "type")
    private String type;
    @Column(name = "breed")
    private String breed;
    @Column(name = "sex")
    private String sex;
    @Column(name = "age")
    private int age;
    @Column(name = "color")
    private String color;
    @Column(name = "name")
    private String name;
    @Column(name = "date")
    private Date date;
    @Column(name = "reportText")
    private String reportText;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Animal() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Animal(long id, String type, String breed, String sex, int age, String color, String name, Date date, String reportText) {
        this.id = id;
        this.type = type;
        this.breed = breed;
        this.sex = sex;
        this.age = age;
        this.color = color;
        this.name = name;
        this.date = date;
        this.reportText = reportText;
    }

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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

     public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", breed='" + breed + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", reportText='" + reportText + '\'' +
                ", user=" + user +
                '}';
    }
}