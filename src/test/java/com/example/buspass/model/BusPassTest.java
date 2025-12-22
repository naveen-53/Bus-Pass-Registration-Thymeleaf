package com.example.buspass.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusPassTest {

    @Test
    void shouldSetAndGetFieldsCorrectly() {
        BusPass pass = new BusPass();

        pass.setId(1);
        pass.setName("John");
        pass.setEmail("john@mail.com");
        pass.setPassType("Monthly");
        pass.setStartDate("2025-01-01");
        pass.setEndDate("2025-01-31");

        assertEquals(1, pass.getId());
        assertEquals("John", pass.getName());
        assertEquals("john@mail.com", pass.getEmail());
        assertEquals("Monthly", pass.getPassType());
        assertEquals("2025-01-01", pass.getStartDate());
        assertEquals("2025-01-31", pass.getEndDate());
    }
}
