package com.example.Assignment.controller;

import com.example.Assignment.model.Manager;
import com.example.Assignment.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
public class ManagerController {

    @Autowired
    public ManagerService managerService;
    @GetMapping("/get_managers")
    public ArrayList<Manager> getManager(){
        return managerService.getManager();
    }
    @PostMapping("/add_manager")
    public Manager addManager(@RequestBody  Manager manager){
        return managerService.addManager(manager);
    }
    @GetMapping("/get_manager/{managerId}")
    public Manager getManagerById(@PathVariable("managerId") UUID managerId){
        return managerService.getManagerById(managerId);
    }
    @PutMapping("/update_manager/{managerId}")
    public Manager updateManager(@RequestBody  Manager manager,@PathVariable("managerId") UUID managerId){
        return managerService.updateManager(manager,managerId);
    }
    @DeleteMapping("/delete_manager/{managerId}")
    public void deleteManager(@PathVariable("managerId") UUID managerId){
        managerService.deleteManager(managerId);
    }
}
