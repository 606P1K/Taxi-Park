package org.stepaniuk.taxipark.API;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stepaniuk.taxipark.Email.Email;
import org.stepaniuk.taxipark.Email.service.EmailService;

import java.time.Year;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Validator {
    public static Map<String,Object> commands = new HashMap<>();
    private static final Logger logger = LogManager.getLogger();
    private static final Email email = new Email(new EmailService());
    private static final String[] command = {"Enter the maximum speed of the car(km/h):","Enter the tank size(L):",
            "Enter the engine volume(m3):","Enter the price of the car($):",
            "Enter the year of car assembly:","Enter the car's fuel consumption per 100 km(L):",
    "Select the type of gasoline that this car consumes:\n1. Regular\n2. Premium\n3. Super\n4. Diesel\n5. Gas"};//car's characteristics
    public static void valid() {
        boolean addToMap;
        Scanner scanner = new Scanner(System.in);
        for (String example: command) {
            System.out.println(example);
            logger.info("Scanning object in Validator!");
            Object object = scanner.nextLine();
            if(object.equals("")||object.equals(" ")){
                logger.error("IllegalArgumentException (You entered Illegal argument!)");
                email.run();
                throw new IllegalArgumentException("You entered Illegal argument!");}
            try{
                addToMap = validator(example,object);
            }catch (NumberFormatException e) {
                logger.error("NumberFormat Exception");
                email.run();
                throw new RuntimeException(e);
            }
            if(addToMap) commands.put(example,object);
            else{
                logger.error("IllegalArgumentException (You entered Illegal argument!)");
                email.run();
                throw new IllegalArgumentException("You entered Illegal argument!");
            }
        }
    }
    private  static boolean validator(String command,Object check){
        logger.info("Parse info in validator!");
        Map<String, Supplier<Boolean>> commandMap = Map.of(
                "Enter the maximum speed of the car(km/h):",()->{
                    int maxSpeed = Integer.parseInt((String) check);
                    return maxSpeed>0&&maxSpeed<490;
                },
                "Enter the tank size(L):",()->{
                    double tankSize = Double.parseDouble((String) check);
                    return tankSize>0&&tankSize<120;
                },
                "Enter the engine volume(m3):",()->{
                    double engineCapacity = Double.parseDouble((String) check);
                    return engineCapacity>0&&engineCapacity<8000;
                },
                "Enter the price of the car($):",()->{
                    double price = Double.parseDouble((String) check);
                    return price>0;
                },
                "Enter the year of car assembly:",()->{
                    int yearOfManufacture = Integer.parseInt((String) check);
                    return yearOfManufacture>1885&&yearOfManufacture<= Year.now().getValue();
                },
                "Enter the car's fuel consumption per 100 km(L):",()->{
                    double fuelConsumption = Double.parseDouble((String) check);
                    return fuelConsumption>0&&fuelConsumption<50;
                },
                "Select the type of gasoline that this car consumes:\n1. Regular\n2. Premium\n3. Super\n4. Diesel\n5. Gas",()->{
                   int value = Integer.parseInt((String) check);
                   return value >= 1 && value < 6;
                }
        );
        return commandMap.get(command).get();
    }
}