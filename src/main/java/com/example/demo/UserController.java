package com.example.demo;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/newUserForm")
    @ResponseBody
    public String setUser(Model model) {
        // create model attribute to bind form data
        User user = userService.userForm();
        model.addAttribute("user", user);
        return "New User Form Completed!";
    }

    @PostMapping("/newUserForm")
    @ResponseBody
    public String saveUser(@ModelAttribute("user") User user) {
        // save user to database
        userService.saveUser(user);
        return "New User Form Submitted!";
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> showUsers() {
        // show all the users
        return userService.getAllUsers();
    }

    @GetMapping("/user/{name}")
    @ResponseBody
    public void showUserDetails(@PathVariable ( value = "name") String name) {
        // show user's details
        User user = userService.getUserByName(name);
        System.out.print(user.getAccountNumber());
        System.out.print(user.getName());
        System.out.print(user.getEmail());
        System.out.print(user.getPhone());
    }

    @GetMapping("/user/{name}/balance")
    @ResponseBody
    public double showUserBalance(@PathVariable ( value = "name") String name) {
        // show user's balance
        return userService.getUserByName(name).getBalance();
    }

    @PostMapping("/user/{name}/deposit")
    @ResponseBody
    public String depositMoney(@PathVariable ( value = "name") String name) {
        // make a deposit
        User user = userService.getUserByName(name);
        userService.depositMoney(user);
        userService.saveUser(user);
        return "Deposited Successfully!";
    }

    @DeleteMapping("/user/{accountNumber}")
    @ResponseBody
    public String deleteUser(@PathVariable (value = "accountNumber") long accountNumber) {
        // call delete user method
        userService.deleteUserByAccountNumber(accountNumber);
        return "Deleted the user successfully!";
    }

    @PutMapping("/user/{name}")
    @ResponseBody
    public String updateName(@PathVariable (value = "name") String name) {
        // call update name method
        userService.updateName(name);
        return "Updated the name successfully!";
    }

    @PutMapping("/user/{email}")
    @ResponseBody
    public String updateEmail(@PathVariable (value = "email") String email) {
        // call update email method
        userService.updateEmail(email);
        return "Updated the email successfully!";
    }

    @PutMapping("/user/{phone}")
    @ResponseBody
    public String updatePhone(@PathVariable (value = "phone") String phone) {
        // call update phone method
        userService.updatePhone(phone);
        return "Updated the phone successfully!";
    }
}
