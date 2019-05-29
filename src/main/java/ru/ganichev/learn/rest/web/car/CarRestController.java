package ru.ganichev.learn.rest.web.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ganichev.learn.rest.model.Car;
import ru.ganichev.learn.rest.model.Person;
import ru.ganichev.learn.rest.service.CarService;
import ru.ganichev.learn.rest.service.PersonService;
import ru.ganichev.learn.rest.web.AbstractRestController;

@RestController
public class CarRestController extends AbstractRestController {
    static final String CREATE_URL = "/car";

    @Autowired
    private PersonService personService;

    @Autowired
    private CarService carService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, value = CREATE_URL)
    public ResponseEntity createPerson(@RequestBody Car car) {
        if (isInputValid(car)) {
            Person tryFindPerson = personService.findById(car.getOwnerId());
            if (tryFindPerson != null && tryFindPerson.getAge() >= 18) {
                Car tryFindCar = carService.findById(car.getId());
                if (tryFindCar == null) {
                    try {
                        carService.create(car);
                    } catch (Exception ex) {
                        return new ResponseEntity(HttpStatus.BAD_REQUEST);
                    }
                    return new ResponseEntity(HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    private boolean isInputValid(@RequestBody Car car) {
        return car.getId() != null
                && car.getModel() != null
                && isValidModelFormat(car.getModel())
                && car.getHorsePower() != null
                && car.getOwnerId() != null
                && car.getHorsePower() > 0;
    }

    private boolean isValidModelFormat(String model) {
        boolean result = false;
        if (model != null && model.contains("-")) {
            int index = model.indexOf("-");
            String vendor = model.substring(0, index);
            if (vendor.length() > 0) {
                String innerModel = model.substring(index + 1);
                if (innerModel.length() > 0) {
                    result = true;
                }
            }
        }
        return result;
    }
}
