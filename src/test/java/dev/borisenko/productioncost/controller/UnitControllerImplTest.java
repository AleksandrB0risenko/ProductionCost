package dev.borisenko.productioncost.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.borisenko.productioncost.controller.impl.TypeControllerImpl;
import dev.borisenko.productioncost.model.Unit;
import dev.borisenko.productioncost.repository.UnitRepo;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UnitControllerImplTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TypeControllerImpl typeController;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UnitRepo unitRepo;

    @BeforeEach
    @AfterEach
    public void resetDB() {
        unitRepo.deleteAll();
    }

    private Unit createTestType(String name) {
        Unit emp = new Unit();
        emp.setName(name);
        emp.setSymbol("Test");
        return unitRepo.save(emp);
    }

    @Test
    public void testGetAll() throws Exception {
        resetDB();
        Unit emp1 = createTestType("Test1");
        Unit emp2 = createTestType("Test2");

        mockMvc.perform(get("/api/unit/get/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(content().json((objectMapper.writeValueAsString(Arrays.asList(emp1, emp2)))))
                .andExpect(jsonPath("$[0].name").value("Test1"))
                .andExpect(jsonPath("$[1].name").value("Test2"));
    }

    @Test
    public void testGetById() throws Exception {
        int id = createTestType("Test3").getId();

        mockMvc.perform(get("/api/unit/get/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Test3"));
    }

    @Test
    public void testAdd() throws Exception {
        assertThat(typeController).isNotNull();

        Unit emp = createTestType("Test4");

        mockMvc.perform(post("/api/unit/add")
                        .content(objectMapper.writeValueAsString(emp))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        Unit emp = createTestType("Test5");

        mockMvc.perform(delete("/api/unit/delete/{id}", emp.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        Unit emp1 = createTestType("Test6");

        Unit emp2 = new Unit();
        emp2.setName("Test7");
        emp2.setSymbol("Test");

        mockMvc.perform(put("/api/unit/update/{id}", emp1.getId())
                        .content(objectMapper.writeValueAsString(emp2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
