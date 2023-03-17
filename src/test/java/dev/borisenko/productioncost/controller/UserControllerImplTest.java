package dev.borisenko.productioncost.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.borisenko.productioncost.controller.impl.TypeControllerImpl;
import dev.borisenko.productioncost.model.User;
import dev.borisenko.productioncost.repository.UserRepo;
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
public class UserControllerImplTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TypeControllerImpl typeController;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepo userRepo;

    @BeforeEach
    @AfterEach
    public void resetDB() {
        userRepo.deleteAll();
    }

    private User createTestType(String name) {
        User emp = new User();
        emp.setUsername(name);
        emp.setFullName("Test");
        emp.setPhone("Test");
        emp.setPassword("Test");
        emp.setEmail("Test");
        emp.setRole("Test");
        return userRepo.save(emp);
    }

    @Test
    public void testGetAll() throws Exception {
        resetDB();
        User emp1 = createTestType("Test1");
        User emp2 = createTestType("Test2");

        mockMvc.perform(get("/api/user/get/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(content().json((objectMapper.writeValueAsString(Arrays.asList(emp1, emp2)))));
    }

    @Test
    public void testGetById() throws Exception {
        int id = createTestType("Test3").getId();

        mockMvc.perform(get("/api/user/get/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Test3"));
    }

    @Test
    public void testAdd() throws Exception {
        assertThat(typeController).isNotNull();

        User emp = createTestType("Test4");

        mockMvc.perform(post("/api/user/add")
                        .content(objectMapper.writeValueAsString(emp))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        User emp = createTestType("Test5");

        mockMvc.perform(delete("/api/user/delete/{id}", emp.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        User emp1 = createTestType("Test6");

        User emp2 = new User();
        emp2.setUsername("Test7");
        emp2.setFullName("Test");
        emp2.setPhone("Test");
        emp2.setPassword("Test");
        emp2.setEmail("Test");
        emp2.setRole("Test");

        mockMvc.perform(put("/api/user/update/{id}", emp1.getId())
                        .content(objectMapper.writeValueAsString(emp2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
