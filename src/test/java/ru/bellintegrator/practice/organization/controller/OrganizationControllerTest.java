package ru.bellintegrator.practice.organization.controller;

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

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(secure = false)
@Sql(scripts = "classpath:data.sql")
public class OrganizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void organizationsWithWrongInnTest() throws Exception {
        String jsonRequest = "{\n" +
                "  \"inn\": \"1234567421312302\",\n" +
                "  \"isActive\": true,\n" +
                "  \"name\": \"Башнефть\"\n" +
                "}";
        String jsonInnErrorValidationResponse = "{\"error\":\"Код ошибки: 400. inn must be 12 characters\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/organization/list").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().json(jsonInnErrorValidationResponse));
    }

    @Test
    public void organizationsWithWrongNameTest() throws Exception {
        String jsonRequest = "{\"name\": \"Б\" }";
        String jsonInnErrorValidationResponse = "{\"error\":\"Код ошибки: 400. name must be more 2 and less than 50 characters\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/organization/list").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().json(jsonInnErrorValidationResponse));
    }

    @Test
    public void organizationsSuccessTest() throws Exception {
        String jsonRequest = "{\"name\": \"Башнефть\"}";
        String jsonResponse = "{\"data\":[{\"id\":1,\"name\":\"Башнефть\",\"isActive\":true}]}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/organization/list").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));
    }

    @Test
    public void organizationByIdSuccessTest() throws Exception {
        String jsonResponse = "{\"data\":" +
                "{\"id\":2," +
                "\"name\":\"Лукойл\"," +
                "\"fullName\":\"ОАО \\\"Лукойл\\\"\"" +
                ",\"inn\":\"123456789012\"" +
                ",\"kpp\":\"123455555\"" +
                ",\"address\":\"Уфа, Менделеева 35\"" +
                ",\"isActive\":true}" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/organization/list/2"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));
    }

    @Test
    public void organizationByWrongIdTest() throws Exception {
        String jsonResponse = "{\"error\":\"Код ошибки: 404. Organization with this id was not found\"}";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/organization/list/35345"))
                .andExpect(MockMvcResultMatchers.status().is(404))
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));
    }

    @Test
    @DirtiesContext
    public void organizationUpdateSuccessTest() throws Exception {
        String request = "{\n" +
                "    \"id\": \"1\",\n" +
                "    \"name\": \"Башнефть\",\n" +
                "    \"fullName\": \"ОАО 'Лукойла'\",\n" +
                "    \"inn\": \"123456789012\",\n" +
                "    \"kpp\": \"987654321\",\n" +
                "    \"address\": \"Ufa, Mendeleeva 32\",\n" +
                "    \"phone\": \"834523451\",\n" +
                "    \"isActive\": \"true\"\n" +
                "}";
        String response = "{\n" +
                "    \"data\": {\n" +
                "        \"result\": \"success\"\n" +
                "    }\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/organization/update").content(request).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    public void organizationUpdateWithWrongKppTest() throws Exception {
        String request = "{\n" +
                "    \"id\": \"1\",\n" +
                "    \"name\": \"Лукойл\",\n" +
                "    \"fullName\": \"ОАО 'Лукойла'\",\n" +
                "    \"inn\": \"123456789012\",\n" +
                "    \"kpp\": \"23\",\n" +
                "    \"address\": \"Ufa, Mendeleeva 32\",\n" +
                "    \"phone\": \"834523451\",\n" +
                "    \"isActive\": \"true\"\n" +
                "}";
        String response = "{\"error\":\"Код ошибки: 400. kpp must be 9 characters\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/organization/update").content(request).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    @DirtiesContext
    public void organizationSaveSuccessTest() throws Exception {
        String request = "{\n" +
                "    \"name\": \"Транснефть\",\n" +
                "    \"fullName\": \"ОАО 'Транснефть'\",\n" +
                "    \"inn\": \"123456789012\",\n" +
                "    \"kpp\": \"987654321\",\n" +
                "    \"address\": \"Ufa, Lenina 77\",\n" +
                "    \"phone\": \"834523451\",\n" +
                "    \"isActive\": \"true\"\n" +
                "}";
        String response = "{\"data\":{\"result\":\"success\"}}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/organization/save").content(request).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    public void organizationSaveWithEmptyAddressWrongTest() throws Exception {
        String request = "{\n" +
                "    \"name\": \"Транснефть\",\n" +
                "    \"fullName\": \"ОАО 'Транснефть'\",\n" +
                "    \"inn\": \"123456789012\",\n" +
                "    \"kpp\": \"987654321\",\n" +
                "    \"phone\": \"834523451\",\n" +
                "    \"isActive\": \"true\"\n" +
                "}";
        String response = "{\"error\":\"Код ошибки: 400. address must be not null or empty\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/organization/save").content(request).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }
}
