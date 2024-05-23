package com.example.geektrust;

import com.example.geektrust.exception.MatchMakingFailedException;
import com.example.geektrust.exception.StartRideFailedException;
import com.example.geektrust.model.Rides;
import com.example.geektrust.service.MatchMaking;
import com.example.geektrust.service.Riding;
import com.example.geektrust.service.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RidingTest {
    User user = new User();
    Rides rides = new Rides();
    Riding riding = new Riding(user, rides);
    MatchMaking matchMaking = new MatchMaking(user);

    @Test
    void ifErrorWhileMatch() {
        assertThrows(MatchMakingFailedException.class,
                () -> matchMaking.match("C1"));
    }

    @Test
    void ifErrorWhileStartRide(){
        assertThrows(StartRideFailedException.class,
                () -> riding.startRiding("RIDE-005", 0,"R9"));
    }
}
