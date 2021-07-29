package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/NewUserForm")
    public String NewUserForm(Model model) {
        // create model attribute to bind form data
        User user = new User();
        model.addAttribute("user", user);
        return "New User Form";
    }

    @PostMapping("/user")
    public String saveUser(@ModelAttribute("user") User user) {
        // save user to database
        userService.saveUser(user);
        return "Saving the specific user...";
    }

    @GetMapping("/users")
    public String showUsers() {
        // show all the users from the service
        List<User> users = userService.getAllUsers();
        return "Showing all the users...";
    }

    @GetMapping("/user/{id}")
    public String showOneUser(@PathVariable ( value = "id") long id) {
        // show a specific user from the service
        User user = userService.getUserById(id);
        return "Showing a specific user...";
    }

    @DeleteMapping("/User/{id}")
    public String deleteUser(@PathVariable (value = "id") long id) {
        // call delete user method
        this.userService.deleteUserById(id);
        return "Deleting...";
    }

}
