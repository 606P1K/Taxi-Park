package org.stepaniuk.taxipark.Park.ControlRoomPackage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.stepaniuk.taxipark.Car.CarType;
import org.stepaniuk.taxipark.Car.GasolineType;
import org.stepaniuk.taxipark.Car.TaxiCar;

import static org.junit.jupiter.api.Assertions.*;

class ControlRoomTest {

    @Test
    @DisplayName("Testing the car reservation")
    void bookCar() {
        TaxiCar taxi = new TaxiCar("Toyota Corola",CarType.STANDARD);
        ControlRoom controlRoom = new ControlRoom();
        controlRoom.bookCar(taxi);

        assertTrue(taxi.isCarBusy());
    }

    @Test
    @DisplayName("Testing of order issuance")
    void giveOrder() {
        TaxiCar taxi = new TaxiCar("Toyota Corola",CarType.STANDARD);
        taxi.setCurrentTank(taxi.getTankCapacity());
        taxi.setFuelConsumptionPerHundred(6.5);
        taxi.setTypeOfGasoline(GasolineType.REGULAR);

        ControlRoom controlRoom = new ControlRoom();
        controlRoom.generateOrders();
        controlRoom.setIndexOfOrder(1);
        controlRoom.giveOrder(taxi);
        int distanceToClient = controlRoom.getOrders().get(1).getDistanceToClient();
        int distanceToPoint = controlRoom.getOrders().get(1).getDistanceToPoint();

        assertEquals((distanceToClient+distanceToPoint),taxi.getMileage());
    }

    @Test
    @DisplayName("Testing the closing of the order")
    void closeOrder() {
        TaxiCar taxi = new TaxiCar("Toyota Corola",CarType.STANDARD);
        ControlRoom controlRoom = new ControlRoom();
        controlRoom.bookCar(taxi);
        controlRoom.closeOrder(taxi);

        assertFalse(taxi.isCarBusy());
    }

    @Test
    @DisplayName("Testing receiving payment for travel")
    void getPaid() {
        TaxiCar taxi = new TaxiCar("Toyota Corola",CarType.STANDARD);
        taxi.setCurrentTank(taxi.getTankCapacity());
        taxi.setFuelConsumptionPerHundred(6.5);
        taxi.setTypeOfGasoline(GasolineType.REGULAR);

        ControlRoom controlRoom = new ControlRoom();
        controlRoom.generateOrders();
        controlRoom.setIndexOfOrder(0);
        controlRoom.giveOrder(taxi);
        int distanceToPoint = controlRoom.getOrders().get(0).getDistanceToPoint();
        controlRoom.getPaid(taxi);

        assertEquals(distanceToPoint*22.9-(distanceToPoint*22.9*0.15),taxi.getProfit());
    }
}