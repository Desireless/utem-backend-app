package cl.utem.inf.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.utem.inf.backend.models.User;
import cl.utem.inf.backend.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }

    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    // Se recibe JSON con formato entidad User
    @PostMapping("/add")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping("getUser")
    public User getUser(String email){
         
        return userService.getUserByEmail(email);
    }
}
