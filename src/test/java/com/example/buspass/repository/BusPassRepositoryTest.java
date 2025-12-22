package com.example.buspass.repository;

import com.example.buspass.model.BusPass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(BusPassRepository.class)
@ActiveProfiles("test")
class BusPassRepositoryTest {

    @Autowired
    private BusPassRepository repository;

    @Test
    void shouldFindAllBusPasses() {
        List<BusPass> passes = repository.findAll();

        assertFalse(passes.isEmpty());
    }

    @Test
    void shouldFindBusPassById() {
        BusPass pass = repository.findById(1);

        assertNotNull(pass);
        assertEquals("Alice", pass.getName());
    }

    @Test
    void shouldReturnNullWhenNotFound() {
        BusPass pass = repository.findById(999);

        assertNull(pass);
    }

    @Test
    void shouldSaveBusPass() {
        BusPass pass = new BusPass();
        pass.setName("Bob");
        pass.setEmail("bob@mail.com");
        pass.setPassType("Weekly");
        pass.setStartDate("2025-02-01");
        pass.setEndDate("2025-02-07");

        int rows = repository.save(pass);

        assertEquals(1, rows);
    }

    @Test
    void shouldUpdateBusPass() {
        BusPass pass = repository.findById(1);
        pass.setName("Updated");

        int rows = repository.update(pass);

        assertEquals(1, rows);
    }

    @Test
    void shouldDeleteBusPass() {
        int rows = repository.delete(1);

        assertEquals(1, rows);
    }
}
