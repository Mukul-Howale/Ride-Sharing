package com.example.geektrust;

import com.example.geektrust.service.User;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertNull;

public class UserTest {

    private final User user = new User();

    @Test
    void checkIfGetDriverIsNull(){
        assertNull(user.getDriver("D1"));
    }

    @Test
    void checkIfGetRiderIsNull(){
        assertNull(user.getRider("R1"));
    }
}
