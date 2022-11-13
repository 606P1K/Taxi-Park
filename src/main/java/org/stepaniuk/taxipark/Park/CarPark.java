package org.stepaniuk.taxipark.Park;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stepaniuk.taxipark.API.Consumer;
import org.stepaniuk.taxipark.API.Supplier;
import org.stepaniuk.taxipark.API.Validator;
import org.stepaniuk.taxipark.Car.CarType;
import org.stepaniuk.taxipark.Car.GasolineType;
import org.stepaniuk.taxipark.Car.TaxiCar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
public class CarPark {
    private List<TaxiCar> cars = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    private double carParkPrice;
    private final Logger logger = LogManager.getLogger();

    public void addCar(){
        logger.info("Add car to the list");
        set(supplierRealisation());
    }

    public TaxiCar supplierRealisation(){
        logger.info("Select the type of car and create an object of the specified type");
        Scanner scan = new Scanner(System.in);
        Supplier<TaxiCar> carSupplier = () ->{
            System.out.print("\nEnter the brand and model of the car: ");
            String name = scan.nextLine();
            System.out.println("\nChoose the type of car:\n1. Standard\n2. Comfort\n3. Business\nYour choice: ");
            int value = scan.nextInt();
            System.out.println("");
            while(value<0 || value > 3){
                System.out.println("Invalid request!");
                value = scan.nextInt();
            }
            return switch (value){
                case 1 -> new TaxiCar(name,CarType.STANDARD);
                case 2 -> new TaxiCar(name,CarType.COMFORT);
                default -> new TaxiCar(name,CarType.BUSINESS);
            };
        };
        return carSupplier.get();
    }

    public void set(TaxiCar car) {
        logger.info("Set the parameters for the car");
        Consumer<TaxiCar> carConsumer = t ->{
            Validator.valid();
            car.setMaxSpeed(Integer.parseInt((String) Validator.commands.get("Enter the maximum speed of the car(km/h):")));
            car.setTankCapacity(Double.parseDouble((String) Validator.commands.get("Enter the tank size(L):")));
            car.setCurrentTank(car.getTankCapacity());
            car.setEngineCapacity(Double.parseDouble((String) Validator.commands.get("Enter the engine volume(m3):")));
            car.setPrice(Double.parseDouble((String) Validator.commands.get("Enter the price of the car($):")));
            carParkPrice+=car.getPrice();
            car.setYearOfManufacture(Integer.parseInt((String) Validator.commands.get("Enter the year of car assembly:")));
            car.setFuelConsumptionPerHundred(Double.parseDouble((String) Validator.commands.get("Enter the car's fuel consumption per 100 km(L):")));
            GasolineType type = GasolineType.getTypeByOrdinal(Integer.parseInt((String) Validator.commands.get("Select the type of gasoline that this car consumes:\n1. Regular\n2. Premium\n3. Super\n4. Diesel\n5. Gas"))-1);
            car.setTypeOfGasoline(type);
            cars.add(car);
    };

        carConsumer.set(car);
    }

}
