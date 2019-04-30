package ru.ganichev.learn.rest.web.person;

import ru.ganichev.learn.rest.model.Person;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PersonTestData {

    public static Person getCreated() {
        return new Person(-1L, "Тестовое имя", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime());
    }
}
