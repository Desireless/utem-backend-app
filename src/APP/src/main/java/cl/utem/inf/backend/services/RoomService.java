package cl.utem.inf.backend.services;

import cl.utem.inf.backend.models.Room;
import cl.utem.inf.backend.repositories.RoomRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio de usuario.
 *
 * @author Juan Pablo Bastías Barahona <mainjpbb@gmail.com>
 */
@Service
public class RoomService {

    /**
     * Repositorio de usuario.
     */
    @Autowired
    private RoomRepository roomRepository;

    /**
     * Devuelve la sala de acuerdo al numero serial de un dispositivo
     *
     * @param deviceSn numero serial del dispositivo
     * @return sala
     */
    public Room getRoom(final String deviceSn) {
        Room room = null;
        if (StringUtils.isNotBlank(deviceSn)) {
            room = roomRepository.findByDeviceSnIgnoreCase(StringUtils.trimToEmpty(deviceSn));
        }
        return room;
    }
}
