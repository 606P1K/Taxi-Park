package org.stepaniuk.taxipark.Park.ControlRoomPackage;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stepaniuk.taxipark.Car.CarType;

import java.util.Random;

@Getter
public class Order {
    public static int countOfOrders;
    private int distanceToPoint;
    private final int idOfOrder;
    private int distanceToClient;
    private final CarType type;
    private final Logger logger = LogManager.getLogger();
    public Order(){
        idOfOrder = countOfOrders;
        countOfOrders++;
        type = generateCarType();
        generateDistance();
    }

    public CarType generateCarType(){
        logger.info("Generate the car type");
        Random random = new Random();
        return CarType.getTypeByOrdinal(random.nextInt(2));
    }

    public void generateDistance(){
        logger.info("Generate the distance");
        Random random = new Random();
        distanceToClient = random.nextInt(2-1)+1;
        distanceToPoint = random.nextInt(10-1)+1;
    }
}
