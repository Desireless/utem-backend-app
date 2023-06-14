package cl.utem.inf.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.utem.inf.backend.models.User;
import cl.utem.inf.backend.services.UserService;

@RestController
public class HomeController {

    @Autowired
    UserService userService;
    
    @GetMapping("api")
    public String sayHello(){
        return "Hello";
    }

    @GetMapping("getUsers")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("addUser")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }
}
