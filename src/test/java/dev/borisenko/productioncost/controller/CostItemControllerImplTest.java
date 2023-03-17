package dev.borisenko.productioncost.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.borisenko.productioncost.controller.impl.TypeControllerImpl;
import dev.borisenko.productioncost.model.CostItem;
import dev.borisenko.productioncost.repository.CostItemRepo;
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
public class CostItemControllerImplTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TypeControllerImpl typeController;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CostItemRepo costItemRepo;

    @BeforeEach
    @AfterEach
    public void resetDB() {
        costItemRepo.deleteAll();
    }

    private CostItem createTestCostItem(String name) {
        CostItem emp = new CostItem();
        emp.setName(name);
        return costItemRepo.save(emp);
    }

    @Test
    public void testGetAll() throws Exception {
        resetDB();
        CostItem emp1 = createTestCostItem("Test1");
        CostItem emp2 = createTestCostItem("Test2");

        mockMvc.perform(get("/api/cost_item/get/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(content().json((objectMapper.writeValueAsString(Arrays.asList(emp1, emp2)))))
                .andExpect(jsonPath("$[0].name").value("Test1"))
                .andExpect(jsonPath("$[1].name").value("Test2"));
    }

    @Test
    public void testGetById() throws Exception {
        int id = createTestCostItem("Test3").getId();

        mockMvc.perform(get("/api/cost_item/get/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Test3"));
    }

    @Test
    public void testAdd() throws Exception {
        assertThat(typeController).isNotNull();

        CostItem emp = createTestCostItem("Test4");

        mockMvc.perform(post("/api/cost_item/add")
                        .content(objectMapper.writeValueAsString(emp))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        CostItem emp = createTestCostItem("Test5");

        mockMvc.perform(delete("/api/cost_item/delete/{id}", emp.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        CostItem emp1 = createTestCostItem("Test6");

        CostItem emp2 = new CostItem();
        emp2.setName("Test7");

        mockMvc.perform(put("/api/cost_item/update/{id}", emp1.getId())
                        .content(objectMapper.writeValueAsString(emp2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
