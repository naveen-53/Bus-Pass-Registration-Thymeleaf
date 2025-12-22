package com.example.buspass.controller;

import com.example.buspass.model.BusPass;
import com.example.buspass.service.BusPassService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BusPassController.class)
class BusPassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BusPassService service;

    @Test
    void shouldShowHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void shouldListAllPasses() throws Exception {
        when(service.getAllBusPasses()).thenReturn(List.of(new BusPass()));

        mockMvc.perform(get("/passes"))
                .andExpect(status().isOk())
                .andExpect(view().name("passes/list"))
                .andExpect(model().attributeExists("passes"));
    }

    @Test
    void shouldViewPass() throws Exception {
        BusPass pass = new BusPass();
        pass.setId(1);

        when(service.getBusPassById(1)).thenReturn(pass);

        mockMvc.perform(get("/passes/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("passes/view"))
                .andExpect(model().attributeExists("pass"));
    }

    @Test
    void shouldRedirectWhenDeleting() throws Exception {
        mockMvc.perform(post("/passes/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/passes"));
    }
}
