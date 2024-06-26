package com.example.geektrust;

import com.example.geektrust.exception.StartRideFailedException;
import com.example.geektrust.model.AllRides;
import com.example.geektrust.service.Riding;
import com.example.geektrust.service.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RidingTest {
    private final User user = new User();
    private final AllRides allRides = new AllRides();
    private final Riding riding = new Riding(user, allRides);
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testInvalidRideWhileStopping() {
        riding.stopRiding("RIDE-001", 2, 2, 10);
        assertEquals("INVALID_RIDE\n", outContent.toString());
    }

    @Test
    void ifErrorWhileStartRide(){
        assertThrows(StartRideFailedException.class,
                () -> riding.startRiding("RIDE-005", 0,"R9"));
    }
}
