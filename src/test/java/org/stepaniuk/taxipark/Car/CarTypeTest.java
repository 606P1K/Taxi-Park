package org.stepaniuk.taxipark.Car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTypeTest {

    @Test
    @DisplayName("Obtaining the car type by index")
    void getTypeByOrdinal() {
        assertEquals(CarType.STANDARD,CarType.getTypeByOrdinal(0));
        assertEquals(CarType.COMFORT,CarType.getTypeByOrdinal(1));
        assertEquals(CarType.BUSINESS, CarType.getTypeByOrdinal(2));
    }

    @Test
    @DisplayName("Exception: Obtaining the car type by index")
    void getTypeByOrdinalException(){
        try {
            CarType.getTypeByOrdinal(-1);
            fail(); // if we got here, no exception was thrown, which is bad
        }
        catch (Exception e) {
            final String expected = "You entered Illegal argument!";
            assertEquals( expected, e.getMessage());
        }
    }
}