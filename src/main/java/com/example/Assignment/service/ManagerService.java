package com.example.Assignment.service;

import com.example.Assignment.model.Manager;
import com.example.Assignment.repository.ManagerJpaRepository;
import com.example.Assignment.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.UUID;

@Service
public class ManagerService implements ManagerRepository {

    @Autowired
    public ManagerJpaRepository managerJpaRepository;


    @Override
    public ArrayList<Manager> getManager() {
        List<Manager> data=managerJpaRepository.findAll();
        ArrayList<Manager> result=new ArrayList<>(data);
        return result;
    }

    @Override
    public Manager addManager(Manager manager) {
       manager.setManagerId(UUID.randomUUID());

        managerJpaRepository.save(manager);
        return manager;
    }

    @Override
    public Manager getManagerById(UUID managerId) {
       try{
           Manager data=managerJpaRepository.findById(managerId).get();
           return  data;
       }
       catch(Exception e){
           throw new ResponseStatusException(HttpStatus.NO_CONTENT);
       }
    }

    @Override
    public Manager updateManager(Manager manager, UUID managerId) {
        try{
            Manager data=managerJpaRepository.findById(managerId).get();
            if(manager.getFullName()!=null){
                data.setFullName(data.getFullName());
            }
            return managerJpaRepository.save(data);

        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

    }

    @Override
    public void deleteManager(UUID managerId) {
        try{
            managerJpaRepository.deleteById(managerId);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"deleted successfully");
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);

    }

}
