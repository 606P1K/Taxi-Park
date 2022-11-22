package org.stepaniuk.taxipark.Command;

import org.stepaniuk.taxipark.Car.TaxiCar;
import org.stepaniuk.taxipark.Park.TaxiPark;

import java.util.List;
import java.util.Scanner;

public class DisplayFilterCarCommand implements Command{
    TaxiPark park;

    public DisplayFilterCarCommand(TaxiPark park) {
        this.park = park;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the lower speed limit: ");
        int lowerSpeed = scanner.nextInt();
        System.out.print("Enter the upper speed limit: ");
        int upperSpeed = scanner.nextInt();

        List<TaxiCar> taxiCars = park.filterCar(lowerSpeed,upperSpeed);
        if(taxiCars.isEmpty())
            System.out.println("\nNo cars with these parameters were found");
        else {
            System.out.println("\nA cars that meets the parameters:");
            taxiCars.forEach(System.out::println);
        }
    }
}
