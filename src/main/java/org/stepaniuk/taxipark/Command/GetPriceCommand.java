package org.stepaniuk.taxipark.Command;

import org.stepaniuk.taxipark.Park.TaxiPark;

public class GetPriceCommand implements Command{
    TaxiPark park;

    public GetPriceCommand(TaxiPark park) {
        this.park = park;
    }

    @Override
    public void execute() {
        park.getPriceOfPark();
    }
}
