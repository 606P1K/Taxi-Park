package org.stepaniuk.taxipark.Park;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stepaniuk.taxipark.Car.TaxiCar;
import org.stepaniuk.taxipark.Park.ControlRoomPackage.ControlRoom;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Data
public class TaxiPark{
    private final CarPark carPark = new CarPark();
    private final ControlRoom controlRoom = new ControlRoom();
    private final Scanner scanner = new Scanner(System.in);
    private final Logger logger = LogManager.getLogger();

    public void formCarPark(){
        logger.info("Add a car to the taxi fleet");
        carPark.addCar();
    }
    public void showCarPark(){
        logger.info("Show the car park of the taxi fleet");
        System.out.println("---------------Car park of the taxi---------------");
        if (carPark.getCars().isEmpty()) System.out.println("\t\t\t\t\t\tEmpty");
        else for (TaxiCar car: carPark.getCars()) System.out.println(car.getCarName());

    }
    public void getPriceOfPark() {
        logger.info("Show the car park price");
        System.out.println("---------------Price of the car park---------------\n"+carPark.getCarParkPrice()+"$");
    }
    public void getProfit(){
        logger.info("Show the profit of the taxipark");
        double profit = carPark.getCars().stream().mapToDouble(TaxiCar::getProfit).sum();
        System.out.println("Profit of your taxi park: "+profit+" UAH");
    }

    public void sortByConsumption() {
        logger.info("Sorting the car park by fuel consumption");
        carPark.getCars().sort(Comparator.comparing(TaxiCar::getFuelConsumptionPerHundred));
    }
    public void showInfo(){
        logger.info("Display information about a certain car");
        System.out.print("Enter the number of the car you want to know about: ");
        int index = scanner.nextInt() - 1;
        if(index > carPark.getCars().size()||index < 0)
            throw new IllegalArgumentException("You entered illegal argument!");
        System.out.println(carPark.getCars().get(index));
    }

    public List<TaxiCar> filterCar(int lowerSpeed, int upperSpeed){
        logger.info("Filter the car park");
        List<TaxiCar> taxiCars = new ArrayList<>();
        for (int i = 0; i < carPark.getCars().size(); i++) {
            if(carPark.getCars().get(i).getMaxSpeed()>lowerSpeed&&carPark.getCars().get(i).getMaxSpeed()<upperSpeed) {
                taxiCars.add(carPark.getCars().get(i));
            }
        }
        return taxiCars;
        //carPark.getCars().stream().filter(car -> car.getMaxSpeed() < upperSpeed ).filter(car -> car.getMaxSpeed() > lowerSpeed).foreach(System.out::println);
    }

    public void displayFilterCars(){
        logger.info("Display the filtered car park");
        System.out.print("Enter the lower speed limit: ");
        int lowerSpeed = scanner.nextInt();
        System.out.print("Enter the upper speed limit: ");
        int upperSpeed = scanner.nextInt();

        List<TaxiCar> taxiCars = filterCar(lowerSpeed,upperSpeed);
        if(taxiCars.isEmpty())
            System.out.println("\nNo cars with these parameters were found");
        else {
            System.out.println("\nA cars that meets the parameters:");
            taxiCars.forEach(System.out::println);
        }
    }
}
