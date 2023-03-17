package dev.borisenko.productioncost.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.borisenko.productioncost.controller.impl.TypeControllerImpl;
import dev.borisenko.productioncost.model.Product;
import dev.borisenko.productioncost.repository.ProductRepo;
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
public class ProductControllerImplTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TypeControllerImpl typeController;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepo productRepo;

    @BeforeEach
    @AfterEach
    public void resetDB() {
        productRepo.deleteAll();
    }

    private Product createTestProduct(String name) {
        Product emp = new Product();
        emp.setName(name);
        emp.setQuantity(1);
        return productRepo.save(emp);
    }

    @Test
    public void testGetAll() throws Exception {
        resetDB();
        Product emp1 = createTestProduct("Test1");
        Product emp2 = createTestProduct("Test2");

        mockMvc.perform(get("/api/product/get/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(content().json((objectMapper.writeValueAsString(Arrays.asList(emp1, emp2)))))
                .andExpect(jsonPath("$[0].name").value("Test1"))
                .andExpect(jsonPath("$[1].name").value("Test2"));
    }

    @Test
    public void testGetById() throws Exception {
        int id = createTestProduct("Test3").getId();

        mockMvc.perform(get("/api/product/get/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Test3"));
    }

    @Test
    public void testAdd() throws Exception {
        assertThat(typeController).isNotNull();

        Product emp = createTestProduct("Test4");

        mockMvc.perform(post("/api/product/add")
                        .content(objectMapper.writeValueAsString(emp))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        Product emp = createTestProduct("Test5");

        mockMvc.perform(delete("/api/product/delete/{id}", emp.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        Product emp1 = createTestProduct("Test6");

        Product emp2 = new Product();
        emp2.setName("Test7");
        emp2.setQuantity(1);

        mockMvc.perform(put("/api/product/update/{id}", emp1.getId())
                        .content(objectMapper.writeValueAsString(emp2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
