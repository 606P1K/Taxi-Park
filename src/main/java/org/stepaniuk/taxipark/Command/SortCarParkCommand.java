package org.stepaniuk.taxipark.Command;

import org.stepaniuk.taxipark.Park.TaxiPark;

public class SortCarParkCommand implements Command{
    TaxiPark park;

    public SortCarParkCommand(TaxiPark park) {
        this.park = park;
    }

    @Override
    public void execute() {
        park.sortByConsumption();
    }
}
