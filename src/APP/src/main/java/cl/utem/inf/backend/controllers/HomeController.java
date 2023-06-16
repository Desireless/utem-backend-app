package cl.utem.inf.backend.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import cl.utem.inf.backend.services.UserService;

@RestController
public class HomeController {

    @Autowired
    UserService userService;
    
    @GetMapping("ping")
    public String sayHello(){
        return "pong";
    }

    
}
