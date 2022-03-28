package com.training.vehicles.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.vehicles.entities.Vehicle;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc // automatyczne skonfigurowanie MockMvc
class VehicleControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    Flyway flyway;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldReturnSelectedVehicle() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/vehicles"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();

        Vehicle[] vehicles = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Vehicle[].class);

        Assertions.assertEquals("Alfa Romeo", vehicles[0].getBrand());

    }

    @Test
    public void shouldReturn404WhenGet() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/vehicles/7"))
                .andExpect(MockMvcResultMatchers.status().is(404))
                .andReturn();

        String actual = Objects.requireNonNull(mvcResult.getResolvedException()).getMessage();

        Assertions.assertEquals("No vehicle with such id: 7", actual);

    }

    @Test
    public void shouldReturnCorrectValue() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/vehicles/2"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
    }


    @Test
    public void shouldAddNewElement() throws Exception {

        Vehicle v1 = new Vehicle();
        v1.setColor("red");
        v1.setBrand("Peugeot");
        v1.setModel("307");
        v1.setId(1L);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/vehicles")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(v1)))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
    }

    @AfterEach
    public void getFlyway() {
        flyway.clean();
        flyway.migrate();
    }

}