package ru.ganichev.learn.rest.web.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ganichev.learn.rest.model.Car;
import ru.ganichev.learn.rest.model.Person;
import ru.ganichev.learn.rest.service.CarService;
import ru.ganichev.learn.rest.service.PersonService;
import ru.ganichev.learn.rest.web.AbstractRestController;
import ru.ganichev.learn.rest.web.dto.StatisticsDto;

import java.util.List;

@RestController
public class StatisticsRestController extends AbstractRestController {
    static final String STATISTICS_URL = "/statistics";

    @Autowired
    private PersonService personService;
    @Autowired
    private CarService carService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = STATISTICS_URL)
    public ResponseEntity<StatisticsDto> getStatistics() throws Exception {
        StatisticsDto statisticsDto = new StatisticsDto();
        List<Car> cars = carService.getAll();
        statisticsDto.setPersoncount(personService.getCount());
        statisticsDto.setCarcount((long) cars.size());
        statisticsDto.setUniquevendorcount(countUniqueVendors(cars));
        return new ResponseEntity<>(statisticsDto, HttpStatus.OK);
    }

    private Long countUniqueVendors(List<Car> cars) {
        return cars.stream().map(Car::getVendor).distinct().count();
    }
}
