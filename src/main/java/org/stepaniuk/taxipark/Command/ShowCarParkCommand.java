package org.stepaniuk.taxipark.Command;

import org.stepaniuk.taxipark.Car.TaxiCar;
import org.stepaniuk.taxipark.Park.TaxiPark;

import java.util.List;

public class ShowCarParkCommand implements Command{
    TaxiPark park;

    public ShowCarParkCommand(TaxiPark park) {
        this.park = park;
    }

    @Override
    public void execute() {
        List<TaxiCar> cars = park.getCars();
        System.out.println("---------------Car park of the taxi---------------");
        if (cars.isEmpty()) System.out.println("\t\t\t\t\t\tEmpty");
        else for (TaxiCar car: cars) System.out.println(car.getCarName());
    }
}
