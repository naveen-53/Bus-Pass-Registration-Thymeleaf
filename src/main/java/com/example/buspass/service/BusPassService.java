package com.example.buspass.service;

import com.example.buspass.model.BusPass;
import java.util.List;

public interface BusPassService {

    List<BusPass> getAllBusPasses();

    BusPass getBusPassById(int id);

    void createBusPass(BusPass pass);

    void updateBusPass(int id, BusPass pass);

    void deleteBusPass(int id);

}
