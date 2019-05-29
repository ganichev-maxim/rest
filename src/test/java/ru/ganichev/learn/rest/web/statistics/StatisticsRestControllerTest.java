package ru.ganichev.learn.rest.web.statistics;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.ganichev.learn.rest.web.AbstractControllerTest;
import ru.ganichev.learn.rest.web.dto.StatisticsDto;
import ru.ganichev.learn.rest.web.person.PersonTestData;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ganichev.learn.rest.TestUtil.contentJson;

public class StatisticsRestControllerTest extends AbstractControllerTest {

    @Test
    public void checkStatistics() throws Exception {

        ResultActions actions = mockMvc.perform(get(StatisticsRestController.STATISTICS_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(contentJson(new StatisticsDto(PersonTestData.PERSON_COUNT, 2L, 1L)));
    }
}
