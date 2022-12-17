package ua.zakharov.restapplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;


@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Transient
    private int age;
    @Column(name = "birthday")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date date;

    public User() {}

    public User(Long id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        LocalDate dob = this.date.toLocalDate();
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
