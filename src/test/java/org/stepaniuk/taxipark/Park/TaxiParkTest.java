package org.stepaniuk.taxipark.Park;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.stepaniuk.taxipark.Car.CarType;
import org.stepaniuk.taxipark.Car.TaxiCar;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaxiParkTest {

    @Test
    @DisplayName("Testing car sorting")
    void sortByConsumption() {
        TaxiPark taxiPark = new TaxiPark();
        TaxiCar taxiCar = new TaxiCar("Toyota Corola", CarType.STANDARD);
        TaxiCar taxiCar1 = new TaxiCar("Toyota LC200",CarType.COMFORT);

        taxiCar.setFuelConsumptionPerHundred(6.5);
        taxiCar1.setFuelConsumptionPerHundred(12.5);

        taxiPark.getCarPark().getCars().add(taxiCar1);
        taxiPark.getCarPark().getCars().add(taxiCar);

        taxiPark.sortByConsumption();
        assertEquals(taxiCar,taxiPark.getCarPark().getCars().get(0));
        assertEquals(taxiCar1,taxiPark.getCarPark().getCars().get(1));

    }

    @Test
    @DisplayName("Testing the filtering of cars")
    void displayFilterCars() {
        TaxiPark taxiPark = new TaxiPark();
        TaxiCar taxiCar = new TaxiCar("Toyota Corola", CarType.STANDARD);
        taxiCar.setMaxSpeed(180);
        taxiPark.getCarPark().getCars().add(taxiCar);

        List<TaxiCar> taxiCars = taxiPark.filterCar(200,250);
        assertTrue(taxiCars.isEmpty());
        taxiCars = taxiPark.filterCar(150,200);
        assertFalse(taxiCars.isEmpty());
    }
}