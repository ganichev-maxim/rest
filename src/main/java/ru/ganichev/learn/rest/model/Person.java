package ru.ganichev.learn.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.ganichev.learn.rest.web.deserializer.CustomDateDeserializer;

import java.util.Date;

public class Person {
    private Long id;
    private String name;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date birthDate;

    public Person() {
    }

    public Person(Long id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
