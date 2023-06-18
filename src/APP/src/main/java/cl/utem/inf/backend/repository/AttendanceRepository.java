package cl.utem.inf.backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.utem.inf.backend.models.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>{


    List<Attendance> findByUserIdAndCreatedAtBetween(Integer userId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
