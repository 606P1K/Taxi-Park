package org.stepaniuk.taxipark.Car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GasolineTypeTest {

    @Test
    @DisplayName("Obtaining the gasoline type by index")
    void getTypeByOrdinal() {
        assertEquals(GasolineType.REGULAR,GasolineType.getTypeByOrdinal(0));
        assertEquals(GasolineType.PREMIUM,GasolineType.getTypeByOrdinal(1));
        assertEquals(GasolineType.SUPER, GasolineType.getTypeByOrdinal(2));
        assertEquals(GasolineType.DIESEL,GasolineType.getTypeByOrdinal(3));
        assertEquals(GasolineType.GAS, GasolineType.getTypeByOrdinal(4));
    }

    @Test
    @DisplayName("Exception: Obtaining the car type by index")
    void getTypeByOrdinalException(){
        try {
            GasolineType.getTypeByOrdinal(-1);
            fail(); // if we got here, no exception was thrown, which is bad
        }
        catch (Exception e) {
            final String expected = "You entered Illegal argument!";
            assertEquals( expected, e.getMessage());
        }
    }
}