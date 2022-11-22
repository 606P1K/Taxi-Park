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
    public List<TaxiCar> getCars(){
        logger.info("Return the car park to display it on the screen in command");
        return carPark.getCars();
    }
    public double getPriceOfPark() {
        logger.info("Return the car park price");
        return carPark.getCarParkPrice();
    }
    public double getProfit(){
        logger.info("Return the profit of the taxipark");
        return carPark.getCars().stream().mapToDouble(TaxiCar::getProfit).sum();
    }

    public void sortByConsumption() {
        logger.info("Sorting the car park by fuel consumption");
        carPark.getCars().sort(Comparator.comparing(TaxiCar::getFuelConsumptionPerHundred));
    }
    public TaxiCar getCarByIndex(int index){
        logger.info("Return car by index");
        if(index > carPark.getCars().size()||index < 0)
            throw new IllegalArgumentException("You entered illegal argument!");
       return carPark.getCars().get(index);
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
}
