package org.stepaniuk.taxipark.Menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stepaniuk.taxipark.Command.Receiver;
import org.stepaniuk.taxipark.Email.Email;
import org.stepaniuk.taxipark.Email.service.EmailService;
import org.stepaniuk.taxipark.Park.TaxiPark;

import java.util.Scanner;

public class OwnerInterface {
    private final TaxiPark park = new TaxiPark();
    private final Scanner scanner = new Scanner(System.in);
    private final Receiver receiver = new Receiver(park);
    private final Logger logger = LogManager.getLogger();
    private final Email email = new Email(new EmailService());
    public void showMenu(){
        logger.info("Accept and execute commands");
        String value;
        park.getControlRoom().generateOrders();
        do {
            park.getControlRoom().orderProcessing(park);
            System.out.print("--------------------------Taxi Park--------------------------\n");
            menu();
            try{
                value = scanner.nextLine();
                while(Integer.parseInt(value) <= 0 ||  Integer.parseInt(value) > 8){
                    System.out.print("Invalid request!\nYour choice: ");
                    value = scanner.nextLine();}
            }catch (NumberFormatException e) {
                logger.error("NumberFormat Exception");
                email.run();
                throw new RuntimeException(e);}
            receiver.run(Integer.parseInt(value)-1);
        } while (true);
    }

    public void menu(){
        logger.info("Display the main menu");
        System.out.println("1.Show car park");
        System.out.println("2.Add a car to the car park");
        System.out.println("3.Get price of car park");
        System.out.println("4.Sort the car park by fuel consumption");
        System.out.println("5.Cars corresponding to the specified speed range");
        System.out.println("6.Display information about the car");
        System.out.println("7.Display profit of the Taxi Park");
        System.out.println("8.Exit\n");
        System.out.print("Your choice: ");
    }
}