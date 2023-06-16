package cl.utem.inf.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.utem.inf.backend.repository.UserRepository;
import cl.utem.inf.backend.models.User;


@Service
public class UserService {
    @Autowired UserRepository userRepository;

    public UserService () {

    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
