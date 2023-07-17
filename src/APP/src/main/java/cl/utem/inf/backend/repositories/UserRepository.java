package cl.utem.inf.backend.repositories;
import cl.utem.inf.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de la entidad User
 *
 * @author Juan Pablo Bastías Barahona <mainjpbb@gmail.com>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{


    /**
     * Funcion que busca un usuario por su correo electrónico
     * @param email Correo electrónico del usuario
     * @return El objeto del usuario
     */
    public User findByEmailIgnoreCase(String email);
    
}
