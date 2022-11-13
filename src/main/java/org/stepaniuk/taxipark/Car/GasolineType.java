package org.stepaniuk.taxipark.Car;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stepaniuk.taxipark.Email.Email;
import org.stepaniuk.taxipark.Email.service.EmailService;

@Getter
public enum GasolineType {
    REGULAR(48),PREMIUM(49),SUPER(51),DIESEL(53),GAS(26);
    private double price = 0;
    private static final Logger logger = LogManager.getLogger();
    private static final Email email = new Email(new EmailService());
    GasolineType(double price){
        this.price = price;
    }

    public static GasolineType getTypeByOrdinal(int ordinal) {
        logger.info("Choose the type of gasoline by index");
        if(ordinal < 0 || ordinal >=GasolineType.values().length){
            logger.error("IllegalArgumentException (You entered Illegal argument!)");
            email.run();
            throw new IllegalArgumentException("You entered Illegal argument!");}
        for(GasolineType t : GasolineType.values()) {
            if(t.ordinal() == ordinal) {
                return t;
            }
        }
        return null;
    }
}
