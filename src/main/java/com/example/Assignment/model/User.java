package com.example.Assignment.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;
import com.example.Assignment.model.Manager;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.UUID;

@Entity
@Table(name="users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name="user_id")
    private UUID userId;
    @Column(name="full_name")
    private String fullName;
    @Column(name="mob_num")
    private String mobNum;
    @Column(name="pan_num")
    private String panNum;


    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;
    @CreationTimestamp
    @Column(name="updated_at")
    private Timestamp updatedAt;

    @Column(name="is_active")
    private boolean isActive=true;

    @Column(name="manager_id")
    private UUID managerId;

   public User(){

   }
   public User(UUID userId,String fullName,String mobNum,String panNum,Timestamp createdAt,Timestamp updatedAt,boolean isActive,UUID managerId ){
       this.userId=userId;
       this.fullName=fullName;
       this.mobNum=mobNum;
       this.isActive=isActive;
       this.createdAt=createdAt;
       this.updatedAt=updatedAt;
       this.panNum=panNum;
       this.managerId=managerId;
   }
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobNum() {
        return mobNum;
    }

    public void setMobNum(String mobNum) {
        this.mobNum = mobNum;
    }

    public String getPanNum() {
        return panNum;
    }

    public void setPanNum(String panNum) {
        this.panNum = panNum;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public UUID getManagerId() {
        return managerId;
    }

    public void setManagerId(UUID managerId) {
        this.managerId = managerId;
    }
}
