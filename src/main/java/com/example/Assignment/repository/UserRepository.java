package com.example.Assignment.repository;
import com.example.Assignment.model.User;
import java.util.*;
public interface UserRepository {
    ArrayList<User> getUser();
    User addUser(User user);
    User getUserById(UUID userId);
    ArrayList<User> updateUser(ArrayList<User> usersList);

    ArrayList<User> getUserData(User userDetails);


    String deleteUser(User userData);
}
