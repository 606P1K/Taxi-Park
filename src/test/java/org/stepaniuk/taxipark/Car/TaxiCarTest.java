package org.stepaniuk.taxipark.Car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxiCarTest {

    @Test
    @DisplayName("Getting the mileage of the trip to the point")
    void driveToPoint() {
        TaxiCar taxi = new TaxiCar("Toyota Corola",CarType.STANDARD);
        taxi.setCurrentTank(taxi.getTankCapacity());
        taxi.setFuelConsumptionPerHundred(6.5);
        taxi.setTypeOfGasoline(GasolineType.REGULAR);
        taxi.driveToPoint(8.0);

        assertEquals(8.0,taxi.getMileage());
    }

    @Test
    @DisplayName("Getting a profit from the trip")
    void fixProfit() {
        TaxiCar taxi = new TaxiCar("Toyota Corola",CarType.STANDARD);
        taxi.fixProfit(8.0);

        assertEquals(155.72,taxi.getProfit());//8.0*22.9-(8.0*22.9*0.15) = 155.72
        //8 - дистанція до клієнта,22.9 - ціна за км,0.15 - процент драйвера
    }

    @Test
    @DisplayName("Car refueling")
    void fillCar() {
        TaxiCar taxi = new TaxiCar("Toyota Corola",CarType.STANDARD);
        taxi.setTankCapacity(40);
        taxi.setCurrentTank(0);
        taxi.setProfit(2000);
        taxi.setTypeOfGasoline(GasolineType.REGULAR);
        taxi.fillCar();

        assertEquals(taxi.getTankCapacity(),taxi.getCurrentTank());//40
        assertEquals(80.0,taxi.getProfit());//2000-(40*48)
        //40 величина баку автомобіля,48 ціна пального
    }
}