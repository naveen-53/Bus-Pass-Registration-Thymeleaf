package com.example.buspass.serviceimpl;

import com.example.buspass.model.BusPass;
import com.example.buspass.repository.BusPassRepository;
import com.example.buspass.service.BusPassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusPassServiceImpl implements BusPassService {

    @Autowired
    private BusPassRepository repo;

    @Override
    public List<BusPass> getAllBusPasses() {
        return repo.findAll();
    }

    @Override
    public BusPass getBusPassById(int id) {
        return repo.findById(id);
    }

    @Override
    public void createBusPass(BusPass pass) {
        repo.save(pass);
    }

    @Override
    public void updateBusPass(int id, BusPass pass) {
        pass.setId(id);
        repo.update(pass);
    }

    @Override
    public void deleteBusPass(int id) {
        repo.delete(id);
    }
}
