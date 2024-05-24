package com.example.geektrust;

import com.example.geektrust.model.AllRides;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertNull;

public class AllRidesTest {
    private final AllRides allRides = new AllRides();

    @Test
    void checkIfAllRidesMapIsNull(){
        assertNull(allRides.getRide("C1"));
    }
}
