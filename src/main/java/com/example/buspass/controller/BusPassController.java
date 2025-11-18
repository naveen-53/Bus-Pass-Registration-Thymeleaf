package com.example.buspass.controller;

import com.example.buspass.model.BusPass;
import com.example.buspass.service.BusPassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class BusPassController {

    @Autowired
    private BusPassService service;

    @GetMapping({"/", "/home", "/index"})
    public String showHome(Model model) {
        String[] quotes = {
                "Travel light. Live light. Spread the light. Be the light.",
                "Every mile is a memory in the making.",
                "The journey is the destination — enjoy the ride.",
                "Hop on, relax, and let the city come to you."
        };
        model.addAttribute("quotes", quotes);
        return "index";
    }

    
    @GetMapping("/passes")
    public String listAll(Model model) {
        List<BusPass> passes = service.getAllBusPasses();
        model.addAttribute("passes", passes);
        return "passes/list";
    }

    @GetMapping("/passes/{id}")
    public String viewPass(@PathVariable("id") int id, Model model) {
        BusPass pass = service.getBusPassById(id);
        model.addAttribute("pass", pass);
        return "passes/view";
    }

    @GetMapping("/passes/new")
    public String showCreateForm(Model model) {
        model.addAttribute("pass", new BusPass());
        model.addAttribute("formAction", "/passes/save");
        model.addAttribute("submitLabel", "Create");
        return "passes/form";
    }

    @GetMapping("/passes/{id}/edit")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        BusPass pass = service.getBusPassById(id);
        if (pass == null) {
            return "redirect:/passes";
        }
        model.addAttribute("pass", pass);
        model.addAttribute("formAction", "/passes/save");
        model.addAttribute("submitLabel", "Update");
        return "passes/form";
    }

    @PostMapping("/passes/save")
    public String savePass(@ModelAttribute BusPass pass) {
        if (pass.getId() == 0) {
            service.createBusPass(pass);
        } else {
            service.updateBusPass(pass.getId(), pass);
        }
        return "redirect:/passes";
    }

    @PostMapping("/passes/{id}/delete")
    public String deletePass(@PathVariable("id") int id) {
        service.deleteBusPass(id);
        return "redirect:/passes";
    }
}
