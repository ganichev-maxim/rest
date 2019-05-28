package ru.ganichev.learn.rest.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.ganichev.learn.rest.web.deserializer.CustomDateDeserializer;

import javax.persistence.*;
import java.util.Date;
@NamedQueries({
        @NamedQuery(name = Person.ALL_SORTED, query = "select p from Person p order by p.id")
})

@Entity
@Table(name = "person")
public class Person extends AbstractBaseEntity {

    public static final String ALL_SORTED = "Person.getAll";
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

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        if (birthDate != null) {
            this.birthDate = new Date(birthDate.getTime());
        } else {
            this.birthDate = null;
        }
    }
}
