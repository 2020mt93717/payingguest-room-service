/***************************************************************************************
 * MIT License
 *
 * Copyright (c) 2022 2020mt93717
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * **************************************************************************************/
package payingguest.room.controller;


import java.math.BigInteger;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import payingguest.room.domain.Room;
import payingguest.room.domain.RoomAllotment;
import payingguest.room.dto.Guest;
import payingguest.room.dto.RoomRequest;
import payingguest.room.service.RoomService;

@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/room")
    public Collection<Room> loadAllRooms() {
        return roomService.loadAllRooms();
    }

    @PostMapping("/room")
    public Room addRoom(@RequestBody RoomRequest room) {
        return roomService.addRoom(room);
    }

    @PutMapping("/room/{roomId}")
    public Room updateRoom(final @PathVariable("roomId") BigInteger roomId, @RequestBody RoomRequest roomRequest) {
        return roomService.updateRoom(roomId, roomRequest);
    }

    @PostMapping("/room/{roomId}/guest/{guestId}")
    public ResponseEntity<RoomAllotment> allotGuest(final @PathVariable("roomId") BigInteger roomId, final @PathVariable("guestId") Long guestId) {
        // Validate if Guest Exist
        String mGuestUrl = "http://guest-service/guest/" + guestId;
        Guest myGuestResponse = restTemplate.getForObject(mGuestUrl, Guest.class);
        if (myGuestResponse != null) {
            return new ResponseEntity<>(roomService.allotGuest(roomId, guestId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/roomallotment")
    public Iterable<RoomAllotment> loadAllAllotments() {
        return roomService.loadAllAllotments();
    }

    @GetMapping("/room/{roomId}")
    public Room findRoomById(final @PathVariable("roomId") BigInteger roomId) {
        return roomService.findRoomById(roomId);

    }
}
