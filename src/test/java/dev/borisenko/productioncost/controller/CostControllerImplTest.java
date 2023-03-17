package dev.borisenko.productioncost.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.borisenko.productioncost.controller.impl.TypeControllerImpl;
import dev.borisenko.productioncost.model.Cost;
import dev.borisenko.productioncost.repository.CostRepo;
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
public class CostControllerImplTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TypeControllerImpl typeController;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CostRepo costRepo;

    @BeforeEach
    @AfterEach
    public void resetDB() {
        costRepo.deleteAll();
    }

    private Cost createTestCost(int price) {
        Cost emp = new Cost();
        emp.setPrice(price);
        emp.setCostItemId(1);
        return costRepo.save(emp);
    }

    @Test
    public void testGetAll() throws Exception {
        resetDB();
        Cost emp1 = createTestCost(1);
        Cost emp2 = createTestCost(2);

        mockMvc.perform(get("/api/cost/get/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(content().json((objectMapper.writeValueAsString(Arrays.asList(emp1, emp2)))));
    }

    @Test
    public void testGetById() throws Exception {
        int id = createTestCost(3).getId();

        mockMvc.perform(get("/api/cost/get/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    public void testAdd() throws Exception {
        assertThat(typeController).isNotNull();

        Cost emp = createTestCost(4);

        mockMvc.perform(post("/api/cost/add")
                        .content(objectMapper.writeValueAsString(emp))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        Cost emp = createTestCost(5);

        mockMvc.perform(delete("/api/cost/delete/{id}", emp.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        Cost emp1 = createTestCost(6);

        Cost emp2 = new Cost();
        emp2.setPrice(7);
        emp2.setCostItemId(1);

        mockMvc.perform(put("/api/cost/update/{id}", emp1.getId())
                        .content(objectMapper.writeValueAsString(emp2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
