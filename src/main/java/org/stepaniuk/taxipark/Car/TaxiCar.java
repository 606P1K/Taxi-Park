package org.stepaniuk.taxipark.Car;

import lombok.Data;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stepaniuk.taxipark.Interface.CarInterface;
@Data
@Getter
public class TaxiCar extends Car implements CarInterface {
    protected double mileage;
    protected double percentageDriver;
    protected double currentTank;
    protected boolean carBusy;
    protected double profit;
    protected CarType carType;
    private final Logger logger = LogManager.getLogger();
    public TaxiCar(String carName, CarType type) {
        this.carName = carName;
        this.carType = type;
        this.percentageDriver = type.getPercentageDriver();
        this.profit = 0;
        this.carBusy = false;
    }

    @Override
    public void driveToPoint(double distanceToPoint){
        logger.info("Driving a car by distance "+distanceToPoint+" km");
        if(currentTank <= 0){
            currentTank = 0;
            fillCar();
        }
        mileage += distanceToPoint;
        currentTank -= distanceToPoint/100 * fuelConsumptionPerHundred;
    }

    public void fixProfit(double distance){
        logger.info("Receive money for transporting the client");
        double withoutDriverPercent = distance*carType.getPricePerKm();
        profit+=withoutDriverPercent - withoutDriverPercent*percentageDriver;
    }
    @Override
    public void fillCar(){
        logger.info("Fill up the car");
        currentTank += tankCapacity;
        profit -= tankCapacity * typeOfGasoline.getPrice();
    }

    @Override
    public String toString(){
        logger.info("Use the method toString(TaxiCar)");
        return  "----------------------------------------"+"\n"+carName +":"+
                "\n    Maximum speed(km/h): " + maxSpeed +
                "\n    Tank capacity(L): " + tankCapacity +
                "\n    Engine Capacity(m3): " + engineCapacity +
                "\n    Price($): " + price +
                "\n    Year of manufacture: " + yearOfManufacture+
                "\n    Real Mileage: "+mileage+
                "\n    The percentage of the car driver: "+percentageDriver+
                "\n    Price per kilometer(UAH): "+carType.getPricePerKm()+
                "\n    Type of gasoline: "+typeOfGasoline+"\n----------------------------------------";
    }
}
