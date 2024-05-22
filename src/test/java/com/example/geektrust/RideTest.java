package com.example.geektrust;

import com.example.geektrust.exception.MatchMakingFailedException;
import com.example.geektrust.exception.StartRideFailedException;
import com.example.geektrust.exception.StopRideFailedException;
import com.example.geektrust.service.Ride;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RideTest {
    Ride ride = new Ride();

    @Test
    void ifErrorWhileMatch() {
        assertThrows(MatchMakingFailedException.class,
                () -> ride.match("C1"));
    }

    @Test
    void ifErrorWhileStartRide(){
        assertThrows(StartRideFailedException.class,
                () -> ride.startRide("RIDE-005", 0,"R9"));
    }

    @Test
    void ifErrorWhileStopRide(){
        assertThrows(StopRideFailedException.class,
                () -> ride.stopRide("RIDE-002", 4, 5, 100));
    }
}
