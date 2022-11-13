package org.stepaniuk.taxipark.Command;

import org.stepaniuk.taxipark.Park.TaxiPark;

public class ShowCarCommand implements Command{
    TaxiPark park;

    public ShowCarCommand(TaxiPark park) {
        this.park = park;
    }

    @Override
    public void execute() {
        park.showInfo();
    }
}
