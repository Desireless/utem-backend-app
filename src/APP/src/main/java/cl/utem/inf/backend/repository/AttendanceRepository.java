package cl.utem.inf.backend.repository;

import cl.utem.inf.backend.models.Attendance;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Juan Pablo Bastías Barahona <mainjpbb@gmail.com>
 */
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    public List<Attendance> findByUserIdAndCreatedAtBetween(Integer userId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
