package com.kpmgbank.training;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomUserServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void usernameParamShouldReturnUser() throws Exception {

        this.mockMvc.perform(get("/users/get/erkinph")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("{\n" +
                        "\"username\": \"erkinph\",\n" +
                        "\"firstname\": \"Erkin\",\n" +
                        "\"lastname\": \"Pehlivan\",\n" +
                        "\"age\": 34\n" +
                        "}"));
    }

    @Test
    public void wrongUsernameParamShouldReturnNull() throws Exception {

        this.mockMvc.perform(get("/users/get/bade").param("name", "Spring Community"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
    }

}