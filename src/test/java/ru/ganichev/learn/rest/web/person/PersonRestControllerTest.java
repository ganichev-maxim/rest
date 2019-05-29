package ru.ganichev.learn.rest.web.person;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.ganichev.learn.rest.model.Person;
import ru.ganichev.learn.rest.service.PersonService;
import ru.ganichev.learn.rest.web.AbstractControllerTest;
import ru.ganichev.learn.rest.web.json.JsonUtil;

import java.text.SimpleDateFormat;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ganichev.learn.rest.TestUtil.contentJson;
import static ru.ganichev.learn.rest.web.person.PersonTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


public class PersonRestControllerTest extends AbstractControllerTest {

    @Autowired
    PersonService personService;

    @Test
    public void testCreatePersonOk() throws Exception {
        Person person = getCreated();
        ResultActions action = mockMvc.perform(post(PersonRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(person)))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        assertMatch(personService.getAll(), person, PERSON2, PERSON3, PERSON_WITHOUT_CAR);
    }

    @Test
    public void testCreatePersonEmptyId() throws Exception {
        Person person = getCreated();
        person.setId(null);
        ResultActions action = mockMvc.perform(post(PersonRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(person)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreatePersonEmptyName() throws Exception {
        Person person = getCreated();
        person.setName(null);
        ResultActions action = mockMvc.perform(post(PersonRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(person)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreatePersonEmptyDate() throws Exception {
        Person person = getCreated();
        person.setBirthDate(null);
        ResultActions action = mockMvc.perform(post(PersonRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(person)))
                .andExpect(status().isBadRequest());
    }
    @Test

    public void testCreatePersonWrongFormatDate() throws Exception {
        Person person = getCreated();
        ResultActions action = mockMvc.perform(post(PersonRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(person, new SimpleDateFormat("yyyy-MM-dd"))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDuplicatePerson() throws Exception {
        Person person = getCreated();
        ResultActions action = mockMvc.perform(post(PersonRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(person)))
                .andExpect(status().isOk());

        assertMatch(personService.getAll(), person, PERSON2, PERSON3, PERSON_WITHOUT_CAR);

        action = mockMvc.perform(post(PersonRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(person)))
                .andExpect(status().isBadRequest());
        assertMatch(personService.getAll(), person, PERSON2, PERSON3, PERSON_WITHOUT_CAR);
    }

    @Test
    public void testWrongJson() throws Exception {
        ResultActions actions = mockMvc.perform(post(PersonRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id2\" : \"5\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""));
    }

    @Test
    public void testGetWithCarsNoParam() throws Exception {
        ResultActions actions = mockMvc.perform(get(PersonRestController.PERSON_WITH_CARS_URL))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetWithCarsWrongFormatParam() throws Exception {
        ResultActions actions = mockMvc.perform(get(PersonRestController.PERSON_WITH_CARS_URL)
                .param(PersonRestController.PARAM_PERSON_ID, "s"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetWithCarsNotFound() throws Exception {
        ResultActions actions = mockMvc.perform(get(PersonRestController.PERSON_WITH_CARS_URL)
                .param(PersonRestController.PARAM_PERSON_ID, String.valueOf(PERSON_NOT_EXIST_ID)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetWithCarsFoundWithoutCars() throws Exception {
        ResultActions actions = mockMvc.perform(get(PersonRestController.PERSON_WITH_CARS_URL)
                .param(PersonRestController.PARAM_PERSON_ID, String.valueOf(PERSON_WITHOUT_CAR_ID)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(contentJson(PERSON_WITHOUT_CAR));
    }

    @Test
    public void testGetWithCarsFoundWithCars() throws Exception {
        ResultActions actions = mockMvc.perform(get(PersonRestController.PERSON_WITH_CARS_URL)
                .param(PersonRestController.PARAM_PERSON_ID, String.valueOf(PERSON2_ID)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(contentJson(PERSON2));
    }
}
