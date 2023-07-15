package cl.utem.inf.backend.controllers.v1;

import cl.utem.inf.backend.models.Attendance;
import cl.utem.inf.backend.services.AttendanceService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;


    @GetMapping("/getAll")
    public List<Attendance> getAttendances() {
        return attendanceService.getAttendances();
    }

    // en caso de no recibir correctamente los parametros retorna 400
    @GetMapping("/user/{userId}/date/{date}")
    public List<Attendance> getAttendancesByUserIdAndDate(
            @PathVariable Integer userId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return attendanceService.getAttendancesByUserIdAndDate(userId, date);
    }

    @PostMapping("/add")
    public ResponseEntity<Attendance> addAttendance(@RequestBody Attendance attendance) {
        Attendance savedAttendance = attendanceService.addAttendance(attendance);
        return new ResponseEntity<>(savedAttendance, HttpStatus.CREATED);
    }
}
