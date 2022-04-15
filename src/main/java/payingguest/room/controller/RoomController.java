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
import org.springframework.web.bind.annotation.*;

import payingguest.room.domain.Room;
import payingguest.room.dto.RoomRequest;
import payingguest.room.service.RoomService;

@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

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

    @GetMapping("/room/{roomId}")
    public Room findRoomById(final @PathVariable("roomId") BigInteger roomId) {
        return roomService.findRoomById(roomId);

    }
}
