package org.stepaniuk.taxipark.Command;

import org.stepaniuk.taxipark.Park.TaxiPark;

public class AddCarCommand implements Command{
    TaxiPark park;

    public AddCarCommand(TaxiPark park) {
        this.park = park;
    }

    @Override
    public void execute() {
        park.formCarPark();
    }
}
