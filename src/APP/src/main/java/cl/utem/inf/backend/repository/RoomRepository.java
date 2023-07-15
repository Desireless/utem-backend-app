package cl.utem.inf.backend.repository;

import cl.utem.inf.backend.models.Campus;
import cl.utem.inf.backend.models.Room;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jpbb
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    public List<Room> findByCampus(Campus campus);

}
