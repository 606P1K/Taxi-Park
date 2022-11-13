package org.stepaniuk.taxipark.Command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stepaniuk.taxipark.Park.TaxiPark;

import java.util.ArrayList;
import java.util.List;

public class Receiver {
    private final List<Command> commandList = new ArrayList<>();
    private final TaxiPark park;
    private final Logger logger = LogManager.getLogger();
    public Receiver(TaxiPark park){
        this.park = park;
        addCommands();
    }
    public void run(int index){
        logger.info("Execute the command at the specified index");
        commandList.get(index).execute();
    }

    public void addCommands(){
        commandList.add(new ShowCarParkCommand(park));
        commandList.add(new AddCarCommand(park));
        commandList.add(new GetPriceCommand(park));
        commandList.add(new SortCarParkCommand(park));
        commandList.add(new DisplayFilterCarCommand(park));
        commandList.add(new ShowCarCommand(park));
        commandList.add(new GetProfitCommand(park));
        commandList.add(new ExitCommand());
    }
}
