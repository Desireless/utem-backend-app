package cl.utem.inf.backend.services;

import cl.utem.inf.backend.models.Room;
import cl.utem.inf.backend.repositories.RoomRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room getRoom(final String deviceSn) {
        Room room = null;
        if (StringUtils.isNotBlank(deviceSn)) {
            room = roomRepository.findByDeviceSnIgnoreCase(StringUtils.trimToEmpty(deviceSn));
        }
        return room;
    }
}
