package ru.bellintegrator.practice.office.controller;

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
import ru.bellintegrator.practice.office.dto.OfficeDto;
import ru.bellintegrator.practice.office.dto.OfficeListFilterDto;
import ru.bellintegrator.practice.office.dto.OfficeToSaveDto;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(secure = false)
@Sql(scripts = "classpath:data.sql")
public class OfficeControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void officeListSuccessTest() throws Exception {
        OfficeListFilterDto officeListFilterDto = new OfficeListFilterDto();
        officeListFilterDto.setOrgId(1L);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/office/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(officeListFilterDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value("АЗС Башнефть №1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].name").value("АЗС Башнефть №2"));
    }

    @Test
    public void officeListWithUnknownOrgIdTest() throws Exception {
        OfficeListFilterDto filter = new OfficeListFilterDto();
        filter.setOrgId(888888L);
        filter.setName("Башнефть");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/office/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(filter)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Organizations with this identifier were not found"));
    }

    @Test
    public void officeByIdSuccessTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/office/list/3"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("АЗС Лукойл №51"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.address").value("Пр.Октября 88"));
    }

    @Test
    public void officeByUnknownIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/office/list/7777"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Office with this identifier was not found"));
    }

    @Test
    @DirtiesContext
    public void officeUpdateSuccessTest() throws Exception {
        OfficeDto office = new OfficeDto();
        office.setId(1L);
        office.setName("Башнефть 777");
        office.setAddress("ул. Ленина, д.777");
        String response = "{\"data\":{\"result\":\"success\"}}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/office/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(office)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    @DirtiesContext
    public void officeUpdateWithEmptyAddressTest() throws Exception {
        OfficeDto office = new OfficeDto();
        office.setId(1L);
        office.setName("Башнефть 777");
        office.setAddress("");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/office/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(office)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("address must be not null or empty; address must be more 2 and less than 100 characters"));
    }

    @Test
    @DirtiesContext
    public void officeSaveSuccessTest() throws Exception {
        OfficeToSaveDto office = new OfficeToSaveDto();
        office.setOrgId(1L);
        office.setName("АЗС Башнефть №888");
        office.setAddress("ул.Некрасова, д.333");
        String response = "{\"data\":{\"result\":\"success\"}}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/office/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(office)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    @DirtiesContext
    public void officeSaveWrongNameTest() throws Exception {
        OfficeToSaveDto office = new OfficeToSaveDto();
        office.setOrgId(1L);
        office.setName("#");
        office.setAddress("ул.Некрасова, д.333");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/office/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(office)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("name must be more 2 and less than 100 characters"));
    }
}
