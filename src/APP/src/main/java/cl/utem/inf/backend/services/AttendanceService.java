package cl.utem.inf.backend.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.utem.inf.backend.models.Attendance;
import cl.utem.inf.backend.models.AttendanceResponse;
import cl.utem.inf.backend.repository.AttendanceRepository;

@Service
public class AttendanceService {
    @Autowired AttendanceRepository attendanceRepository;

    public AttendanceService() {

    }

    public List<Attendance> getAttendances() {
        return attendanceRepository.findAll();
    }

    public Attendance addAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public List<AttendanceResponse> getAttendancesByUserIdAndDate(Integer userId, LocalDate date) {
        LocalDateTime startDateTime = date.atStartOfDay();
        LocalDateTime endDateTime = date.atTime(LocalTime.MAX);
        return attendanceRepository.findByUserIdAndCreatedAtBetween(userId, startDateTime, endDateTime);
    }

}
