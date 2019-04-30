package ru.ganichev.learn.rest.web.person;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ganichev.learn.rest.model.Person;

import java.util.Date;

@RestController
public class PersonRestController {
    static final String CREATE_URL = "/person";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = CREATE_URL)
    public ResponseEntity createPerson(@RequestBody Person person) {
        if (person.getId() == null || person.getBirthDate() == null || person.getName() == null
                || person.getBirthDate().after(new Date())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
