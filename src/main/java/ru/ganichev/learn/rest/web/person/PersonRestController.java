package ru.ganichev.learn.rest.web.person;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ganichev.learn.rest.model.Person;
import ru.ganichev.learn.rest.service.PersonService;
import ru.ganichev.learn.rest.web.AbstractRestController;

import java.util.Date;

@RestController
public class PersonRestController extends AbstractRestController {
    public static final String CREATE_URL = "/person";
    static final String PERSON_WITH_CARS_URL = "/personwithcars";
    public static final String CLEAR_ALL = "/clear";
    static final String PARAM_PERSON_ID = "personid";

    @Autowired
    private PersonService personService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, value = CREATE_URL)
    public ResponseEntity createPerson(@RequestBody Person person) {

        if (isInputValid(person)) {
            Person tryFindPerson = personService.findById(person.getId());
            if (tryFindPerson == null) {
                try {
                    personService.create(person);
                }
                catch (Exception ex) {
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = PERSON_WITH_CARS_URL)
    public ResponseEntity<Person> getPersonWithCars(@RequestParam(name = PARAM_PERSON_ID) Long personId) {
        if (personId != null) {
            Person person = personService.findById(personId, Person.WITH_CARS_GRAPH);
            if (person != null) {
                return new ResponseEntity<>(person, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = CLEAR_ALL)
    public ResponseEntity clearAll() {
        personService.deleteAll();
        return new ResponseEntity(HttpStatus.OK);
    }

    private boolean isInputValid(@RequestBody Person person) {
        return person.getId() != null && person.getBirthDate() != null && person.getName() != null
            && !person.getBirthDate().after(new Date());
    }
}
