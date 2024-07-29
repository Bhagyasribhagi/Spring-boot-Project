package com.example.Assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import  com.example.Assignment.model.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<User, UUID> {


}
