package org.stepaniuk.taxipark.Park.ControlRoomPackage;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stepaniuk.taxipark.Car.TaxiCar;
import org.stepaniuk.taxipark.Park.CarPark;
import org.stepaniuk.taxipark.Park.TaxiPark;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class ControlRoom{
    private int indexOfCar;
    private boolean haveFreeCar;
    private int indexOfOrder = 0;
    private final List<Order> orders = new ArrayList<>();
    private final Logger logger = LogManager.getLogger();

    public void takeOrder(CarPark carPark) {
        logger.info("Accept the order and check the availability of the car of the selected type");
        if(indexOfOrder>=(orders.size()-1)) indexOfOrder = 0;
        for (TaxiCar car : carPark.getCars()) {
            if(orders.get(indexOfOrder).getType().equals(car.getCarType())&& !car.isCarBusy()){
                bookCar(carPark.getCars().get(indexOfCar));
                haveFreeCar = true;
                giveOrder(carPark.getCars().get(indexOfCar));
                break;
            }
            indexOfCar++;
        }
    }

    public void bookCar(TaxiCar car) {
        logger.info("Reserve a car");
        car.setCarBusy(true);
    }

    public void giveOrder(TaxiCar car) {
        logger.info("Give an order for a car (we use the driveToPoint method)");
            car.driveToPoint(orders.get(indexOfOrder).getDistanceToClient());//To client
            car.driveToPoint(orders.get(indexOfOrder).getDistanceToPoint());//To point
    }

    public void closeOrder(TaxiCar car) {
        logger.info("Remove the reservation from the car");
       car.setCarBusy(false);
       haveFreeCar = false;
    }

    public void getPaid(TaxiCar car){
        logger.info("Receive payment for the order");
        car.fixProfit(orders.get(indexOfOrder).getDistanceToPoint());
    }

    public void generateOrders(){
        logger.info("Generate orders");
        Random random = new Random();
        for (int i = 0; i < random.nextInt(50-1)+1; i++) //random.nextInt(50-1)+1
            orders.add(new Order());
    }

    public void orderProcessing(TaxiPark park){
        logger.info("Processing the order");
        Random rand = new Random();
        for (int i = 0; i < rand.nextInt(orders.size()-1)+1; i++) {
            if(!park.getCarPark().getCars().isEmpty()){
               takeOrder(park.getCarPark());
                if(isHaveFreeCar()){
                    getPaid(park.getCarPark().getCars().get(indexOfCar));
                    closeOrder(park.getCarPark().getCars().get(indexOfCar));
                }
                setIndexOfCar(0);
            }
            indexOfOrder++;
        }
    }

}
