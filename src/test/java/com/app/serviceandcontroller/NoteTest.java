package com.app.serviceandcontroller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NoteTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void unauthenticatedUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/note"))
                .andExpect(status().isForbidden())
                .andDo(
                        mvcResult -> mockMvc.perform(post("/api/note/add")).andExpect(status().isForbidden())
                )
                .andDo(
                        mvcResult -> mockMvc.perform(delete("/api/note/delete/{id}", 1)).andExpect(status().isForbidden())
                );
    }

    @Test
    void getAllNotes() {
    }
}