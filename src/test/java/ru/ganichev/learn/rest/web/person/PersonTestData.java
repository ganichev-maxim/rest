package ru.ganichev.learn.rest.web.person;

import ru.ganichev.learn.rest.model.Car;
import ru.ganichev.learn.rest.model.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonTestData {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static final Long PERSON2_ID = 2L;
    public static final Long PERSON3_ID = 3L;
    public static final Long PERSON_NOT_EXIST_ID = -2L;
    public static final Long PERSON_WITHOUT_CAR_ID = 4L;
    public static Person PERSON2;
    public static Person PERSON3;
    public static Person PERSON_WITHOUT_CAR;
    private static String[] ignoreFields = {"cars"};

    static {
        try {
            PERSON2 = new Person(PERSON2_ID, "user", sdf.parse("2001-05-30"));
            PERSON2.getCars().add(new Car(2L, "BMW-X3", 400, PERSON2_ID));
            PERSON3 = new Person(PERSON3_ID, "user2", sdf.parse("1999-04-30"));
            PERSON3.getCars().add(new Car(3L, "BMW-X6", 500, PERSON3_ID));
            PERSON_WITHOUT_CAR = new Person(PERSON_WITHOUT_CAR_ID, "user4", sdf.parse("1999-04-30"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static Person getCreated() {
        return new Person(-1L, "Тестовое имя", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime());
    }

    public static void assertMatch(Person actual, Person expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, ignoreFields);
    }

    public static void assertMatch(Iterable<Person> actual, Person... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    private static <T> void assertMatch(Iterable<Person> actual, Iterable<Person> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields(ignoreFields).isEqualTo(expected);
    }
}
