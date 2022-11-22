package org.stepaniuk.taxipark.Command;

import org.stepaniuk.taxipark.Car.TaxiCar;
import org.stepaniuk.taxipark.Park.TaxiPark;

import java.util.Scanner;

public class ShowCarCommand implements Command{
    TaxiPark park;

    public ShowCarCommand(TaxiPark park) {
        this.park = park;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of the car you want to know about: ");
        int index = scanner.nextInt() - 1;
        TaxiCar taxiCar = park.getCarByIndex(index);
        System.out.println(taxiCar);
    }
}
