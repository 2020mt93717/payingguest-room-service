package payingguest.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import payingguest.room.domain.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, java.math.BigInteger>{

}
