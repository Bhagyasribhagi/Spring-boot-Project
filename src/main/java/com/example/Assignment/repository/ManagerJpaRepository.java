package com.example.Assignment.repository;

import com.example.Assignment.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import  com.example.Assignment.model.User;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ManagerJpaRepository extends JpaRepository<Manager,UUID> {


}
