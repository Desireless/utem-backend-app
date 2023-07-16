package cl.utem.inf.backend.services;

import cl.utem.inf.backend.models.Attendance;
import cl.utem.inf.backend.domains.AttendanceResponse;
import cl.utem.inf.backend.models.User;
import cl.utem.inf.backend.repositories.AttendanceRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public AttendanceService() {

    }

    public List<Attendance> getAttendances(final User user) {
        return attendanceRepository.findByUser(user);
    }

    public Attendance addAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public List<AttendanceResponse> getAttendancesByUserIdAndDate(Integer userId, LocalDate date) {
        final ZoneOffset zo = ZoneOffset.of(ZoneId.systemDefault().getId());
        OffsetDateTime startDateTime = date.atStartOfDay().atOffset(zo);
        OffsetDateTime endDateTime = date.atTime(LocalTime.MAX).atOffset(zo);
        return attendanceRepository.searchByUserIdAndCreatedAtBetween(userId, startDateTime, endDateTime);
    }
}
