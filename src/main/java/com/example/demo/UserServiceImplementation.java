package com.example.demo;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByName(String name) {
        Optional<User> optional = userRepository.findByName(name);
        User user;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException("User Not Found");
        }
        return user;
    }

    @Override
    public void depositMoney(User user) {
        System.out.print("Please enter your deposit amount.");
        double deposit = new Scanner(System.in).nextDouble();
        if(deposit >= 5.0 && deposit <= 10000.0) {
            user.setBalance(user.getBalance() + deposit);
        } else {
            throw new RuntimeException("Invalid Deposit Amount!");
        }
    }


    @Override
    public User userForm() {
        User newUser = new User();
        System.out.print("Please enter your account number, name, email and phone number.");
        newUser.setAccountNumber(new Scanner(System.in).nextLong());
        newUser.setName(new Scanner(System.in).next());
        newUser.setEmail(new Scanner(System.in).next());
        newUser.setPhone(new Scanner(System.in).next());
        newUser.setBalance(0.0);
        return newUser;
    }

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void deleteUserByAccountNumber(long accountNumber) {
        this.userRepository.deleteById(accountNumber);
    }

    @Override
    public void updateName(String name) {
        Optional<User> optional = userRepository.findByName(name);
        User user;
        if (optional.isPresent()) {
            user = optional.get();
            System.out.print("Please enter your new name.");
            user.setName(new Scanner(System.in).next());
            this.userRepository.save(user);
        } else {
            throw new RuntimeException("User Not Found");
        }
    }

    @Override
    public void updateEmail(String email) {
        Optional<User> optional = userRepository.findByEmail(email);
        User user;
        if (optional.isPresent()) {
            user = optional.get();
            System.out.print("Please enter your new email.");
            user.setEmail(new Scanner(System.in).next());
            this.userRepository.save(user);
        } else {
            throw new RuntimeException("User Not Found");
        }
    }

    @Override
    public void updatePhone(String phone) {
        Optional<User> optional = userRepository.findByPhone(phone);
        User user;
        if (optional.isPresent()) {
            user = optional.get();
            System.out.print("Please enter your new phone number.");
            user.setPhone(new Scanner(System.in).next());
            this.userRepository.save(user);
        } else {
            throw new RuntimeException("User Not Found");
        }
    }

}