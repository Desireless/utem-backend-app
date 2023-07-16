package cl.utem.inf.backend.controllers.v1;

import cl.utem.inf.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    UserService userService;
}
