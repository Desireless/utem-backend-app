package cl.utem.inf.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.utem.inf.backend.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    // JPA permite crear estos metodos de forma automática con el nombre de la función
    User findByEmail(String email);
    
}
