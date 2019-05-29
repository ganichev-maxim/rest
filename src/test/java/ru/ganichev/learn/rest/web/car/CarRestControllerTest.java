package ru.ganichev.learn.rest.web.car;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.ganichev.learn.rest.model.Car;
import ru.ganichev.learn.rest.model.Person;
import ru.ganichev.learn.rest.web.AbstractControllerTest;
import ru.ganichev.learn.rest.web.json.JsonUtil;
import ru.ganichev.learn.rest.web.person.PersonRestController;
import ru.ganichev.learn.rest.web.person.PersonTestData;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ganichev.learn.rest.web.car.CarTestData.getCreated;

public class CarRestControllerTest extends AbstractControllerTest {

    @Test
    public void testCreateCarEmptyId() throws Exception {
        Car car = getCreated();
        car.setId(null);
        ResultActions action = mockMvc.perform(post(CarRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(car)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateCarEmptyModel() throws Exception {
        Car car = getCreated();
        car.setModel(null);
        ResultActions action = mockMvc.perform(post(CarRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(car)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateCarWrongFormatModel() throws Exception {
        Car car = getCreated();
        car.setModel("X5");
        ResultActions action = mockMvc.perform(post(CarRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(car)))
                .andExpect(status().isBadRequest());
        car.setModel("-X5");
        action = mockMvc.perform(post(CarRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(car)))
                .andExpect(status().isBadRequest());
        car.setModel("BMW-");
        action = mockMvc.perform(post(CarRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(car)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateCarEmptyHorsePower() throws Exception {
        Car car = getCreated();
        car.setHorsePower(null);
        ResultActions action = mockMvc.perform(post(CarRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(car)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateCarEmptyOwnerId() throws Exception {
        Car car = getCreated();
        car.setOwnerId(null);
        ResultActions action = mockMvc.perform(post(CarRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(car)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testWrongJson() throws Exception {
        ResultActions actions = mockMvc.perform(post(CarRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id2\" : \"5\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""));
    }

    @Test
    public void testHorsePowerBelowOrEqualZero() throws Exception {
        Car car = getCreated();
        car.setHorsePower(0);
        ResultActions action = mockMvc.perform(post(CarRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(car)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateCatNoOwner() throws Exception {
        Car car = getCreated();
        car.setOwnerId(PersonTestData.PERSON_NOT_EXIST_ID);
        ResultActions action = mockMvc.perform(post(CarRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(car)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateCarInvalidAge() throws Exception {
        Car car = getCreated();
        Person person = PersonTestData.getCreated();
        Date invalidDate = Date.from(LocalDate.now().minusYears(3).atStartOfDay(ZoneId.systemDefault()).toInstant());
        person.setBirthDate(invalidDate);
        car.setOwnerId(person.getId());
        ResultActions action = mockMvc.perform(post(PersonRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(person)))
                .andExpect(status().isOk());
        action = mockMvc.perform(post(CarRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(car)))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testCreateCarOK() throws Exception {
        Car car = getCreated();
        Person person = PersonTestData.getCreated();
        Date validDate = Date.from(LocalDate.now().minusYears(19).atStartOfDay(ZoneId.systemDefault()).toInstant());
        person.setBirthDate(validDate);
        car.setOwnerId(person.getId());
        ResultActions action = mockMvc.perform(post(PersonRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(person)))
                .andExpect(status().isOk());
        action = mockMvc.perform(post(CarRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(car)))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateCarDuplicate() throws Exception {
        Car car = getCreated();
        Person person = PersonTestData.getCreated();
        Date validDate = Date.from(LocalDate.now().minusYears(19).atStartOfDay(ZoneId.systemDefault()).toInstant());
        person.setBirthDate(validDate);
        car.setOwnerId(person.getId());
        ResultActions action = mockMvc.perform(post(PersonRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(person)))
                .andExpect(status().isOk());
        action = mockMvc.perform(post(CarRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(car)))
                .andExpect(status().isOk());
        action = mockMvc.perform(post(CarRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(car)))
                .andExpect(status().isBadRequest());
    }
}
