package com.example.Assignment.repository;
import com.example.Assignment.model.Manager;
import java.util.*;
public interface ManagerRepository {
    ArrayList<Manager> getManager();
    Manager addManager(Manager manager);
    Manager getManagerById(UUID managerId);
    Manager updateManager(Manager manager,UUID managerId);
    void deleteManager(UUID managerId);

}
