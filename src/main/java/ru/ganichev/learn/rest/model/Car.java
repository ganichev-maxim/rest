package ru.ganichev.learn.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = Car.ALL_SORTED, query = "select c from Car c order by c.id")
})
@Entity
@Table(name = "car")
public class Car extends AbstractBaseEntity {

    public static final String ALL_SORTED = "Car.getAll";
    private Long id;
    private String model;
    private Integer horsePower;
    private Long ownerId;

    public Car() {
    }

    public Car(Long id, String model, Integer horsePower, Long ownerId) {
        this.id = id;
        this.model = model;
        this.horsePower = horsePower;
        this.ownerId = ownerId;
    }

    @Id
    @Column(name = "id")
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "horse_power")
    @JsonProperty("horsepower")
    public Integer getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }

    @Column(name = "owner_id")
    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
