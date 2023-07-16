package cl.utem.inf.backend.controllers.v1;

import cl.utem.inf.backend.models.User;
import cl.utem.inf.backend.services.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    UserService userService;



    @GetMapping("/getAll")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    // Se recibe JSON con formato entidad User
    @PostMapping("/add")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // Se recibe JSON con clave valor email: <email>
    @PostMapping("/getByEmail")
    public ResponseEntity<User> getUser(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        User user = userService.getUserByEmail(email);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }
}
