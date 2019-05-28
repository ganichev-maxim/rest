package ru.ganichev.learn.rest.web.person;

import ru.ganichev.learn.rest.model.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonTestData {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static Long PERSON2_ID = 2L;
    public static Long PERSON_NOT_EXIST_ID = -2L;
    public static Person PERSON2;
    public static Person PERSON3;

    static {
        try {
            PERSON2 = new Person(PERSON2_ID, "user", sdf.parse("2001-05-30"));
            PERSON3 = new Person(PERSON2_ID + 1, "user2", sdf.parse("1999-04-30"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static Person getCreated() {
        return new Person(-1L, "Тестовое имя", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime());
    }

    public static void assertMatch(Person actual, Person expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }

    public static void assertMatch(Iterable<Person> actual, Person... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    private static <T> void assertMatch(Iterable<Person> actual, Iterable<Person> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields().isEqualTo(expected);
    }
}
