package com.example.Assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.UUID;
import java.util.*;

@Entity
@Table(name="manager")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name="manager_id")
    private UUID managerId;
    @Column(name="full_name")
    private String fullName;

    @OneToMany
    @JoinColumn(name="manager_id")

    @JsonIgnoreProperties("managerId")

    private List<User> users=new ArrayList<>();
    public Manager(){

    }
    public Manager(UUID managerId,String fullName,List<User> users){
        this.managerId=managerId;
        this.fullName=fullName;
        this.users=users;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public UUID getManagerId() {
        return managerId;
    }

    public void setManagerId(UUID managerId) {
        this.managerId = managerId;
    }
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


}
