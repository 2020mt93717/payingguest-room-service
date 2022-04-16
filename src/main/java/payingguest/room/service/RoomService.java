package payingguest.room.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import payingguest.room.domain.Room;
import payingguest.room.dto.RoomRequest;
import payingguest.room.repository.RoomRepository;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Collection<Room> loadAllRooms() {
        return roomRepository.findAll();
    }


    public Room findRoomById(BigInteger roomId) {
        Optional<Room> room =  roomRepository.findById(roomId);
        if(room.isPresent()) {
            return room.get();
        }
        return null;
    }

    public Room addRoom(RoomRequest roomRequest) {
        Room room = objectMapper(roomRequest);
        room.setCreationDate(new Date());
        room.setLastUpdatedDate(new Date());
        return  roomRepository.save(room);
    }

    public Room updateRoom(BigInteger roomId, RoomRequest roomRequest) {
        Room roomInDb = findRoomById(roomId);

        if(roomInDb != null) {
            if(StringUtils.hasText(roomRequest.getRoomNumber())) {
                roomInDb.setRoomNumber(roomRequest.getRoomNumber());
            }

            if(StringUtils.hasText(roomRequest.getApartmentName())) {
                roomInDb.setApartmentName(roomRequest.getApartmentName());
            }
            if(!Objects.isNull(roomRequest.getMaximumOccupancy())) {
                roomInDb.setMaximumOccupancy(roomRequest.getMaximumOccupancy());
            }
            if(!Objects.isNull(roomRequest.getRoomRent())) {
                roomInDb.setRoomRent(roomRequest.getRoomRent());
            }
            if(StringUtils.hasText(roomRequest.getLastUpdatedBy())) {
                roomInDb.setLastUpdatedBy(roomRequest.getLastUpdatedBy());
            }
            roomInDb.setLastUpdatedDate(new Date());
            return  roomRepository.save(roomInDb);
        }
        return null;

    }

    public static Room objectMapper(RoomRequest roomRequest){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Room room = mapper.convertValue(roomRequest, Room.class);
        return room;
    }
}
