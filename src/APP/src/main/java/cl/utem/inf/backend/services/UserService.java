package cl.utem.inf.backend.services;

import cl.utem.inf.backend.models.User;
import cl.utem.inf.backend.repository.UserRepository;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    public User getUserByEmail(final String email) {
        User user = null;
        final String text = StringUtils.lowerCase(StringUtils.trimToEmpty(email));
        if (StringUtils.isNotBlank(text)) {
            user = userRepository.findByEmailIgnoreCase(text);
        }
        return user;
    }
}
