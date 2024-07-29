package com.example.Assignment.service;

import com.example.Assignment.repository.ManagerJpaRepository;
import com.example.Assignment.repository.UserJpaRepository;
import com.example.Assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Assignment.model.User;
import java.util.*;
import java.util.UUID;
import java.util.regex.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.example.Assignment.model.Manager;

@Service
public class UserService implements UserRepository {

    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private ManagerJpaRepository managerJpaRepository;

    @Override
    public ArrayList<User> getUser(){
        List<User> data=userJpaRepository.findAll();
        ArrayList<User> result=new ArrayList<>(data);
        return result;

    }
    @Override
    public User addUser(User user) {
        user.setUserId(UUID.randomUUID());// getting random userId
        try {

            if (user.getFullName() == null || user.getFullName().trim().isEmpty()) {
                throw new IllegalArgumentException("Full name must not be empty");
            }


            String phoneNum = user.getMobNum();
            if (phoneNum == null || !isValidPhoneNumber(phoneNum)) {
                throw new IllegalArgumentException("Phone number must not be empty");
            }
            phoneNum = phoneNum.replaceAll("[^\\d]", "");
            if (phoneNum.startsWith("91") && phoneNum.length() == 12) {
                phoneNum = phoneNum.substring(2);
            } else if (phoneNum.startsWith("0") && phoneNum.length() == 11) {
                phoneNum = phoneNum.substring(1);
            }


            if (phoneNum.length() != 10) {
                throw new IllegalArgumentException("Invalid phone number length");
            }
            user.setMobNum(phoneNum);


            String panNum = user.getPanNum();
            String panNum_capitals = panNum.toUpperCase();
            if (!isValidPanNumber(panNum_capitals)) {
                throw new IllegalArgumentException("Invalid PAN number format");
            }
            user.setPanNum(panNum_capitals);
            return userJpaRepository.save(user);


        } catch (IllegalArgumentException e) {

            System.err.println("validation error"+e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }
    }

    private boolean isValidPhoneNumber(String phoneNum) {

        return phoneNum.matches("^(0|\\+91)?\\d{10}$");
    }
    private boolean isValidPanNumber(String panNum) {
        String regex = "^[A-Z]{5}\\d{4}[A-Z]$";
        return Pattern.matches(regex, panNum);
    }


    @Override
    public User getUserById(UUID userId) {
        try {
            User data = userJpaRepository.findById(userId).get();
            return data;
        }
      catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
       }
    }
    public User checkingUserByMobileNumb(String mobileNum){
        List<User> usersList = userJpaRepository.findAll();
        for(User user : usersList){
            if(mobileNum.equals(user.getMobNum())){
                return user;
            }
        }
        return null;
    }

    public boolean validatingMobileNumber(String mobNumber){
        int lengthOfMobNum = mobNumber.length();
        if((lengthOfMobNum == 11 && mobNumber.startsWith("0")) ||  (lengthOfMobNum == 13 && mobNumber.startsWith("+91")) || (lengthOfMobNum == 10)){
            int i=lengthOfMobNum-1;
            for(int j=0;j<10;j++){
                if(!Character.isDigit(mobNumber.charAt(i))){
                    return false;
                }
                i=i-1;
            }
            return true;
        }
        else{
            return false;
        }

    }
    public String getModifiedMobileNumber(String mobNumber){
        int i=mobNumber.length()-1;
        String modifiedMobileNumber = "";
        for(int j=0;j<10;j++){
            modifiedMobileNumber = mobNumber.charAt(i)+modifiedMobileNumber;
            i=i-1;
        }
        return modifiedMobileNumber;
    }
    public boolean isExistingMobileNum(String modifiedMobileNumber){
        List<User> users = userJpaRepository.findAll();
        for(User user : users){
            if(modifiedMobileNumber.equals(user.getMobNum())){
                return true;
            }
        }
        return false;
    }
    public boolean isExistingPanNumber(String panNumber){
        List<User> users = userJpaRepository.findAll();
        for(User user : users){
            if(panNumber.equals(user.getPanNum())){
                return true;
            }
        }
        return false;
    }
    public boolean validatingPanNumber(String panNumber){
        for(char ch : panNumber.toCharArray()){
            if(!Character.isLetter(ch)  &&   !Character.isDigit(ch)) {
                return false;
            }
        }
        return panNumber.length() == 10;
    }

    @Override
    public ArrayList<User> updateUser(ArrayList<User> usersList) {
        ArrayList<UUID> managerIds = new ArrayList<>();
        ArrayList<UUID> userIds = new ArrayList<>();

        for(User userData:usersList){
            if(userData.getManagerId()==null) {
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"can't update");
            }
            try {
                managerJpaRepository.findById(userData.getManagerId()).get();
                userIds.add(userData.getUserId());
            }
            catch(NoSuchElementException e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"manager id not found");
            }
        }

        List<User> existingUsersList = userJpaRepository.findAllById(userIds);

        if(existingUsersList.size() != userIds.size()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"One or more Users are NOT FOUND");
        }
        User existingUser = null;
        ArrayList<User> updatedUsersList = new ArrayList<>();

        for(User user : usersList){
            existingUser = userJpaRepository.findById(user.getUserId()).get();
            if(existingUser.getManagerId() != null){
                existingUser.setIsActive(false);
            }
            if(user.getFullName() != null){
                String userFullName = user.getFullName();
                if(!userFullName.isEmpty()){
                    existingUser.setFullName(userFullName);
                }
                else{
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not a Valid Name");
                }
            }
            if(user.getMobNum() != null){
                String mobNumber  = user.getMobNum();
                if(validatingMobileNumber(mobNumber)){
                    String modifiedMobileNumber = getModifiedMobileNumber(mobNumber);
                    if(isExistingMobileNum(modifiedMobileNumber)){
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Duplicate Mobile Number found for one or more users");
                    }
                    existingUser.setMobNum(modifiedMobileNumber);
                }
                else{
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not a Valid Mobile Number");
                };
            }

            if(user.getPanNum() != null){
                String panNumber = user.getPanNum();
                if(validatingPanNumber(panNumber)){
                    if(isExistingPanNumber(panNumber.toUpperCase())){
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Duplicate PAN Number found for one or more users");
                    }
                    existingUser.setPanNum(panNumber.toUpperCase());
                }
                else{
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not a Valid PAN Number");
                }
            }
            existingUser.setManagerId(user.getManagerId());
            updatedUsersList.add(existingUser);
        }
        userJpaRepository.saveAll(updatedUsersList);
        return updatedUsersList;
    }





    @Override
    public ArrayList<User> getUserData(User userDetails){
        ArrayList<User> users = new ArrayList<>();
        User newUser = null;
        if(userDetails.getUserId() != null) {
            try {
                UUID userId=userDetails.getUserId();
                newUser = userJpaRepository.findById(userId).get();
                users.add(newUser);
            } catch (NoSuchElementException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with the provided userId");
            }
        }
        else if(userDetails.getMobNum() != null) {
            newUser = checkingUserByMobileNumb(userDetails.getMobNum());
            if (newUser == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with the provided mobileNumber");
            }
            users.add(newUser);
        }
        else{
            Manager manager = managerJpaRepository.findById(userDetails.getManagerId()).get();
            List<User> managerData = manager.getUsers();
            users = new ArrayList<>(managerData);
        }
        return users;
    }




    @Override

public String deleteUser(User userData){
    if(userData.getUserId() != null) {
        try {
            userJpaRepository.findById(userData.getUserId()).get();
            userJpaRepository.deleteById(userData.getUserId());
            return "User with ID " + userData.getUserId() + " has been successfully deleted.";

        } catch (NoSuchElementException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with the provided userId");
            //return "User not found by userId";
        }
    }

    else if(userData.getMobNum() != null) {

        User existingUser = checkingUserByMobileNumb(userData.getMobNum());

        if (existingUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with the provided mobileNumber");
            //return "User not found by mobile number";
        }
        userJpaRepository.deleteById(existingUser.getUserId());
        return "User with mobile number " + userData.getMobNum() + " has been successfully deleted.";
    }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User identifier missing.");
    }
}
