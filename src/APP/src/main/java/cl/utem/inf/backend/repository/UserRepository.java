package cl.utem.inf.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.utem.inf.backend.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
