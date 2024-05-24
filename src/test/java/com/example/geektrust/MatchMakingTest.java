package com.example.geektrust;

import com.example.geektrust.exception.MatchMakingFailedException;
import com.example.geektrust.model.AllRides;
import com.example.geektrust.service.MatchMaking;
import com.example.geektrust.service.Riding;
import com.example.geektrust.service.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MatchMakingTest {
    private final User user = new User();
    private final MatchMaking matchMaking = new MatchMaking(user);
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
        matchMaking.match("R1");
        assertEquals("NO_DRIVERS_AVAILABLE\n", outContent.toString());
    }
}
