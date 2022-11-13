package org.stepaniuk.taxipark.Car;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stepaniuk.taxipark.Email.Email;
import org.stepaniuk.taxipark.Email.service.EmailService;

@Getter
public enum CarType {
    STANDARD(22.9,0.15),COMFORT(26,0.2),BUSINESS(31.6,0.25);
    private double pricePerKm = 0;
    private double percentageDriver = 0;
    private static final Logger logger = LogManager.getLogger();
    private static final Email email = new Email(new EmailService());

    CarType(double pricePerKm,double percentageDriver) {
        this.pricePerKm = pricePerKm;
        this.percentageDriver = percentageDriver;
    }

    public static CarType getTypeByOrdinal(int ordinal) {
        if(ordinal < 0 || ordinal >=CarType.values().length){
            logger.error("IllegalArgumentException (You entered Illegal argument!)");
            email.run();
            throw new IllegalArgumentException("You entered Illegal argument!");
        }
        for(CarType t : CarType.values()) {
            if(t.ordinal() == ordinal) {
                return t;
            }
        }
        return null;
    }
}
