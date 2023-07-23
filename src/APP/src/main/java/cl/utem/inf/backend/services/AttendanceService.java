package cl.utem.inf.backend.services;

import cl.utem.inf.backend.domains.AttendanceResponse;
import cl.utem.inf.backend.models.Attendance;
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

/**
 * Servicio de usuario.
 *
 * @author Juan Pablo Bastías Barahona <mainjpbb@gmail.com>
 */
@Service
public class AttendanceService {

    /**
     * Repositorio de asistencia.
     */
    @Autowired
    private AttendanceRepository attendanceRepository;

    /**
     * Constructor.
     */
    public AttendanceService() {
    }

    /**
     * Devuelve las asistencias de un usuario.
     *
     * @param user usuario
     * @return asistencias del usuario
     */
    public List<Attendance> getAttendances(final User user) {
        return attendanceRepository.findByUser(user);
    }

    /**
     * agrega una asistencia al sistema.
     *
     * @param attendance objeto asistencia
     * @return asistencias del usuario
     */
    public Attendance addAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    /**
     * Devuelve las asistencias de un usuario en una fecha.
     *
     * @param userId identificador del usuario
     * @param date fecha
     * @return asistencias del usuario por fecha
     */
    public List<AttendanceResponse> getAttendancesByUserIdAndDate(Integer userId, LocalDate date) {
        final ZoneOffset zo = ZoneOffset.of(ZoneId.systemDefault().getId());
        OffsetDateTime startDateTime = date.atStartOfDay().atOffset(zo);
        OffsetDateTime endDateTime = date.atTime(LocalTime.MAX).atOffset(zo);
        return attendanceRepository.searchByUserIdAndCreatedAtBetween(userId, startDateTime, endDateTime);
    }
}
