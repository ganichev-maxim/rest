package ru.ganichev.learn.rest.web.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ganichev.learn.rest.model.Person;
import ru.ganichev.learn.rest.service.PersonService;

import java.util.Date;

@RestController
public class PersonRestController {
    static final String CREATE_URL = "/person";

    @Autowired
    private PersonService personService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = CREATE_URL)
    public ResponseEntity createPerson(@RequestBody Person person) {

        if (person.getId() == null || person.getBirthDate() == null || person.getName() == null
                || person.getBirthDate().after(new Date())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Person tryFindPerson = personService.findById(person.getId());
        if (tryFindPerson!= null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        personService.create(person);
        return new ResponseEntity(HttpStatus.OK);
    }
}
