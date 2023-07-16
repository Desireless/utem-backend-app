package cl.utem.inf.backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import cl.utem.inf.backend.models.Attendance;
import cl.utem.inf.backend.models.AttendanceResponse;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>{

    // el new genera la instancia para poder hacer uso de AttendanceResponse y hacer el JOIN con Room
    @Query("SELECT new cl.utem.inf.backend.models.AttendanceResponse(a, r.roomName) " +
            "FROM Attendance a " +
            "JOIN Room r ON a.roomId = r.id " +
            "WHERE a.userId = :userId AND a.createdAt BETWEEN :startDateTime AND :endDateTime")
    List<AttendanceResponse> findByUserIdAndCreatedAtBetween(Integer userId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
