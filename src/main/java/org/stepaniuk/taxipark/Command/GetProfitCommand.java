package org.stepaniuk.taxipark.Command;

import org.stepaniuk.taxipark.Park.TaxiPark;

public class GetProfitCommand implements Command{
    TaxiPark park;

    public GetProfitCommand(TaxiPark park) {
        this.park = park;
    }

    @Override
    public void execute() {
        park.getProfit();
    }
}
