package com.example.demo;

import java.util.List;

public interface UserService {
    User userForm();
    void saveUser(User user);
    List<User> getAllUsers();
    User getUserByName(String name);
    void depositMoney(User user);
    void deleteUserByAccountNumber(long id);
    void updateName(String name);
    void updateEmail(String email);
    void updatePhone(String phone);
}