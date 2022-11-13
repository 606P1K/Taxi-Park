package org.stepaniuk.taxipark.Car;

import lombok.Data;

@Data
public abstract class Car{
    protected String carName;
    protected int maxSpeed;
    protected double tankCapacity;
    protected double engineCapacity;
    protected double price;
    protected int yearOfManufacture;
    protected double fuelConsumptionPerHundred;
    protected GasolineType typeOfGasoline;

}
