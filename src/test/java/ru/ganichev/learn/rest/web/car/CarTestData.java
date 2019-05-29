package ru.ganichev.learn.rest.web.car;

import ru.ganichev.learn.rest.model.Car;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.ganichev.learn.rest.web.person.PersonTestData.PERSON2_ID;
import static ru.ganichev.learn.rest.web.person.PersonTestData.PERSON3_ID;

public class CarTestData {

    public static final Long CAR2_ID = 2L;
    public static final Long CAR3_ID = 3L;
    public static final Long CAR_NOT_EXIST_ID = -2L;

    public static final Car CAR2 = new Car(CAR2_ID, "BMW-X3", 400, PERSON2_ID);
    public static final Car CAR3 = new Car(CAR3_ID, "BMW-X6", 500, PERSON3_ID);

    public static Car getCreated() {
        return new Car(-1L, "BMW-X5", 400, 2L);
    }

    public static void assertMatch(Iterable<Car> actual, Car... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    private static <T> void assertMatch(Iterable<Car> actual, Iterable<Car> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields().isEqualTo(expected);
    }

    public static void assertMatch(Car actual, Car expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }
}
