package cl.utem.inf.backend.repository;
import cl.utem.inf.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{


    /**
     * JPA permite crear estos metodos de forma automática con el nombre de la función
     * @param email Correo electrónico del usuario a buscar
     * @return El objeto del usuario por su correo electrónico
     */
    public User findByEmailIgnoreCase(String email);
    
}
