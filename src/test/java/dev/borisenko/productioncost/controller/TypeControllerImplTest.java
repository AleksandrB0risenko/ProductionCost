package dev.borisenko.productioncost.controller;

import dev.borisenko.productioncost.controller.impl.TypeControllerImpl;
import dev.borisenko.productioncost.model.Type;
import dev.borisenko.productioncost.repository.TypeRepo;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TypeControllerImplTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TypeControllerImpl typeController;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TypeRepo typeRepo;

    @BeforeEach
    @AfterEach
    public void resetDB() {
        typeRepo.deleteAll();
    }

    private Type createTestType(String name) {
        Type emp = new Type();
        emp.setName(name);
        return typeRepo.save(emp);
    }

    @Test
    public void testGetAll() throws Exception {
        resetDB();
        Type emp1 = createTestType("Test1");
        Type emp2 = createTestType("Test2");

        mockMvc.perform(get("/api/type/get/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(content().json((objectMapper.writeValueAsString(Arrays.asList(emp1, emp2)))))
                .andExpect(jsonPath("$[0].name").value("Test1"))
                .andExpect(jsonPath("$[1].name").value("Test2"));
    }

    @Test
    public void testGetById() throws Exception {
        int id = createTestType("Test3").getId();

        mockMvc.perform(get("/api/type/get/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Test3"));
    }

    @Test
    public void testAdd() throws Exception {
        assertThat(typeController).isNotNull();

        Type emp = createTestType("Test4");

        mockMvc.perform(post("/api/type/add")
                        .content(objectMapper.writeValueAsString(emp))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        Type emp = createTestType("Test5");

        mockMvc.perform(delete("/api/type/delete/{id}", emp.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        Type emp1 = createTestType("Test6");

        Type emp2 = new Type();
        emp2.setName("Test7");

        mockMvc.perform(put("/api/type/update/{id}", emp1.getId())
                        .content(objectMapper.writeValueAsString(emp2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
