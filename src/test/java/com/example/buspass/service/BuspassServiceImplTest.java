package com.example.buspass.service;

import com.example.buspass.model.BusPass;
import com.example.buspass.repository.BusPassRepository;
import com.example.buspass.serviceimpl.BusPassServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BusPassServiceImplTest {

    @Mock
    private BusPassRepository repository;

    @InjectMocks
    private BusPassServiceImpl service;

    @Test
    void shouldReturnAllBusPasses() {
        when(repository.findAll()).thenReturn(List.of(new BusPass()));

        List<BusPass> result = service.getAllBusPasses();

        assertEquals(1, result.size());
        verify(repository).findAll();
    }

    @Test
    void shouldReturnBusPassById() {
        BusPass pass = new BusPass();
        pass.setId(1);

        when(repository.findById(1)).thenReturn(pass);

        BusPass result = service.getBusPassById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void shouldCreateBusPass() {
        BusPass pass = new BusPass();

        service.createBusPass(pass);

        verify(repository).save(pass);
    }

    @Test
    void shouldUpdateBusPass() {
        BusPass pass = new BusPass();

        service.updateBusPass(5, pass);

        assertEquals(5, pass.getId());
        verify(repository).update(pass);
    }

    @Test
    void shouldDeleteBusPass() {
        service.deleteBusPass(3);

        verify(repository).delete(3);
    }
}
