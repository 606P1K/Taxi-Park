package org.stepaniuk.taxipark.Command;

import org.stepaniuk.taxipark.Park.TaxiPark;

public class ShowCarParkCommand implements Command{
    TaxiPark park;

    public ShowCarParkCommand(TaxiPark park) {
        this.park = park;
    }

    @Override
    public void execute() {
        park.showCarPark();
    }
}
