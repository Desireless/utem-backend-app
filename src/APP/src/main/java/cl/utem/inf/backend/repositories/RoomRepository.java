package cl.utem.inf.backend.repositories;

import cl.utem.inf.backend.models.Campus;
import cl.utem.inf.backend.models.Room;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de la entidad Room
 *
 * @author Juan Pablo Bastías Barahona <mainjpbb@gmail.com>
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    /**
     * Busca sala por numero serial de dispositivo
     *
     * @param deviceSn numero serial
     * @return sala encontrada
     */
    public Room findByDeviceSnIgnoreCase(String deviceSn);

    /**
     * Busca sala por campus
     *
     * @param campus campus id
     * @return sala encontrada
     */
    public List<Room> findByCampus(Campus campus);

}
