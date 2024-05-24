package com.example.geektrust;

import com.example.geektrust.model.AllRides;
import com.example.geektrust.service.Bill;
import com.example.geektrust.service.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

public class BillTest {
    private final User user = new User();
    private final AllRides allRides = new AllRides();
    private final Bill bill = new Bill(user,allRides);
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
    public void testInvalidRideForBillCalculation() {
        bill.calculateBill("RIDE-001");
        assertEquals("INVALID_RIDE\n", outContent.toString());
    }
}
