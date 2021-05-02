package ru.bellintegrator.practice.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.user.dto.UserListFilterDto;
import ru.bellintegrator.practice.user.dto.UserToSaveDto;
import ru.bellintegrator.practice.user.dto.UserToUpdateDto;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(secure = false)
@Sql(scripts = "classpath:data.sql")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void usersSuccessTest() throws Exception {
        UserListFilterDto filter = new UserListFilterDto();
        filter.setOfficeId(2L);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(filter)))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].secondName").value("Olegov"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].position").value("manager"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].secondName").value("Kozlova"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].position").value("cleaner"));
    }

    @Test
    public void usersUnknownOfficeIdTest() throws Exception {
        UserListFilterDto filter = new UserListFilterDto();
        filter.setOfficeId(777L);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(filter)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Office with this identifier was not found"));
    }

    @Test
    public void usersWrongSecondNameTest() throws Exception {
        UserListFilterDto filter = new UserListFilterDto();
        filter.setOfficeId(1L);
        filter.setSecondName("G");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(filter)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("secondName must be more 2 and less than 50 characters"));
    }

    @Test
    public void userByIdSuccessTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/2"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName").value("Ivan"));
    }

    @Test
    public void userByUnknownIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/787878"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is(404))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("User with this identifier was not found"));
    }

    @Test
    @DirtiesContext
    public void userUpdateSuccessTest() throws Exception {
        UserToUpdateDto user = new UserToUpdateDto();
        user.setId(1L);
        user.setFirstName("Fedor");
        user.setPosition("engineer");
        String response = "{\"data\":{\"result\":\"success\"}}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    @DirtiesContext
    public void userUpdateUnknownIdTest() throws Exception {
        UserToUpdateDto user = new UserToUpdateDto();
        user.setId(666L);
        user.setFirstName("Fedor");
        user.setPosition("engineer");
        String response = "{\"error\":\"User with such identifier was not found\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().is(404))
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    @DirtiesContext
    public void userSaveSuccessTest() throws Exception {
        UserToSaveDto user = new UserToSaveDto();
        user.setOfficeId(1L);
        user.setFirstName("Валентин");
        user.setSecondName("Иванов");
        user.setMiddleName("Иванович");
        user.setPosition("engineer");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.result").value("success"));
    }

    @Test
    @DirtiesContext
    public void userSaveWrongFirstNameTest() throws Exception {
        UserToSaveDto user = new UserToSaveDto();
        user.setOfficeId(1L);
        user.setFirstName("В");
        user.setSecondName("Иванов");
        user.setMiddleName("Иванович");
        user.setPosition("engineer");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("firstName must be more 2 and less than 50 characters"));
    }

    @Test
    @DirtiesContext
    public void userSaveUnknownOfficeIdTest() throws Exception {
        UserToSaveDto user = new UserToSaveDto();
        user.setOfficeId(777L);
        user.setFirstName("Валерий");
        user.setPosition("cleaner");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Office with this identifier was not found"));
    }
}
