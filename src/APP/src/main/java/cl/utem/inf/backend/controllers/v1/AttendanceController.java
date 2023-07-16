package cl.utem.inf.backend.controllers.v1;

import cl.utem.inf.backend.models.Attendance;
import cl.utem.inf.backend.domains.AttendanceResponse;
import cl.utem.inf.backend.domains.AttendanceVO;
import cl.utem.inf.backend.exceptions.BadCredentialException;
import cl.utem.inf.backend.exceptions.NotFoundException;
import cl.utem.inf.backend.models.User;
import cl.utem.inf.backend.services.AttendanceService;
import cl.utem.inf.backend.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/v1/attendance"},
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class AttendanceController {

    @Autowired
    private UserService userService;

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping(value = {"/all"},
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AttendanceVO>> getAttendances(HttpServletRequest request,
            @RequestHeader("Authorization") String authorization) {

        final User authUser = userService.authUser(authorization);
        if (authUser == null) {
            throw new BadCredentialException("Usuario inválido");
        }

        List<Attendance> attendances = attendanceService.getAttendances(authUser);
        if (CollectionUtils.isEmpty(attendances)) {
            throw new NotFoundException("No hay asistencia");
        }

        List<AttendanceVO> vos = new ArrayList<>();
        for (Attendance a : attendances) {
            vos.add(new AttendanceVO(a));
        }
        attendances.clear();

        return ResponseEntity.ok(vos);
    }

    // en caso de no recibir correctamente los parametros retorna 400
    @GetMapping("/user/{userId}/date/{date}")
    public List<AttendanceResponse> getAttendancesByUserIdAndDate(HttpServletRequest request,
            @RequestHeader("Authorization") String authorization,
            @PathVariable Integer userId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return attendanceService.getAttendancesByUserIdAndDate(userId, date);
    }

//    @PostMapping("/add")
//    public ResponseEntity<Attendance> addAttendance(HttpServletRequest request,
//            @RequestHeader("Authorization") String authorization,
//            @RequestBody Attendance attendance) {
//
//        Attendance savedAttendance = attendanceService.addAttendance(attendance);
//        return new ResponseEntity<>(savedAttendance, HttpStatus.CREATED);
//    }
}
