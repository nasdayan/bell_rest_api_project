package ru.bellintegrator.practice.doc.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.bellintegrator.practice.Application;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(secure = false)
@Sql(scripts = "classpath:data.sql")
public class DocControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void docSuccessTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/docs").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value("Паспорт"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].code").value("23"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].name").value("Военный билет"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[2].name").value("Водительское удостоверение"));
    }
}
