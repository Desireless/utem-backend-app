package cl.utem.inf.backend.controllers.v1;

import cl.utem.inf.backend.models.Attendance;
import cl.utem.inf.backend.domains.AttendanceVO;
import cl.utem.inf.backend.domains.GeoVO;
import cl.utem.inf.backend.exceptions.BadCredentialException;
import cl.utem.inf.backend.exceptions.NotFoundException;
import cl.utem.inf.backend.models.Room;
import cl.utem.inf.backend.models.User;
import cl.utem.inf.backend.services.AttendanceService;
import cl.utem.inf.backend.services.RoomService;
import cl.utem.inf.backend.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    private RoomService roomService;

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
        Collections.sort(vos, (AttendanceVO first, AttendanceVO second) -> first.getDate().compareTo(second.getDate()));

        return ResponseEntity.ok(vos);
    }

    @PostMapping(value = {"{deviceSn}/request"},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AttendanceVO> addAttendance(HttpServletRequest request,
            @RequestHeader("Authorization") String authorization,
            @PathVariable("deviceSn") String deviceSn,
            @RequestBody GeoVO body) {

        final User authUser = userService.authUser(authorization);
        if (authUser == null) {
            throw new BadCredentialException("Usuario inválido");
        }

        final Room room = roomService.getRoom(deviceSn);
        if (room == null) {
            throw new NotFoundException(String.format("La sala (%s) no se encontró", deviceSn));
        }

        // @TODO: Validar la ubicación geográfica
        Attendance attendance = new Attendance();
        attendance.setLatitude(body.getLatitude());
        attendance.setLongitude(body.getLongitude());
        attendance.setUser(authUser);
        attendance.setRoom(room);

        Attendance savedAttendance = attendanceService.addAttendance(attendance);
        AttendanceVO vo = new AttendanceVO(savedAttendance);
        return ResponseEntity.ok(vo);
    }
}
