package cl.utem.inf.backend.repositories;

import cl.utem.inf.backend.models.Attendance;
import cl.utem.inf.backend.domains.AttendanceResponse;
import cl.utem.inf.backend.models.User;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Juan Pablo Bastías Barahona <mainjpbb@gmail.com>
 */
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // el new genera la instancia para poder hacer uso de AttendanceResponse y hacer el JOIN con Room
    @Query("SELECT new cl.utem.inf.backend.domains.AttendanceResponse(a) FROM Attendance a "
            + "WHERE a.user.id = :userId AND a.createdAt BETWEEN :startDateTime AND :endDateTime")
    public List<AttendanceResponse> searchByUserIdAndCreatedAtBetween(Integer userId, OffsetDateTime startDateTime, OffsetDateTime endDateTime);

    public List<Attendance> findByUser(User user);

}
