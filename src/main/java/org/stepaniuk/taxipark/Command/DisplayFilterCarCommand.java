package org.stepaniuk.taxipark.Command;

import org.stepaniuk.taxipark.Park.TaxiPark;

public class DisplayFilterCarCommand implements Command{
    TaxiPark park;

    public DisplayFilterCarCommand(TaxiPark park) {
        this.park = park;
    }

    @Override
    public void execute() {
        park.displayFilterCars();
    }
}
