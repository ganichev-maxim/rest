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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
                .andExpect(status().isOk());

        assertMatch(personService.getAll(), person, PERSON2, PERSON3);
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

        assertMatch(personService.getAll(), person, PERSON2, PERSON3);

        action = mockMvc.perform(post(PersonRestController.CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(person)))
                .andExpect(status().isBadRequest());
        assertMatch(personService.getAll(), person, PERSON2, PERSON3);
    }
}
