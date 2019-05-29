package ru.ganichev.learn.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.ganichev.learn.rest.web.deserializer.CustomDateDeserializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Person.ALL_SORTED, query = "select p from Person p order by p.id"),
        @NamedQuery(name = Person.ALL_SORTED_WITH_CARS, query = "select distinct p from Person p left join fetch p.cars order by p.id"),
        @NamedQuery(name = Person.COUNT, query = "select count(p) from Person p")
})

@NamedEntityGraph(name = Person.WITH_CARS_GRAPH,
        attributeNodes = @NamedAttributeNode("cars"))
@Entity
@Table(name = "person")
@JsonPropertyOrder({"id", "name", "birthdate", "cars"})
public class Person extends AbstractBaseEntity {

    public static final String ALL_SORTED = "Person.getAll";
    public static final String ALL_SORTED_WITH_CARS = "Person.getAllWithCars";
    public static final String COUNT = "Person.getCount";
    public static final String WITH_CARS_GRAPH = "graph.Person.cars";
    private Long id;
    private String name;
    private List<Car> cars = new ArrayList<>();

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
    @JsonProperty("birthdate")
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

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            mappedBy = "ownerId")
    //@JoinColumn(name = "owner_id")
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Transient
    @JsonIgnore
    public Integer getAge() {
        LocalDate now = LocalDate.now();
        LocalDate birth = this.birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return (int) ChronoUnit.YEARS.between(birth, now);
    }
}
