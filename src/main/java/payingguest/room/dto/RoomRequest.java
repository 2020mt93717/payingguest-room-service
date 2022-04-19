package payingguest.room.dto;

import lombok.*;
import java.util.Date;

@Setter
@Getter
public class RoomRequest {

    private String apartmentName;

    private String roomNumber;

    private long maximumOccupancy;

    private long roomRent;

    private String createdBy;

    private String lastUpdatedBy;

    private Long guestId;
}
