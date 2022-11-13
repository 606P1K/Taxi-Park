package org.stepaniuk.taxipark.Command;

public class ExitCommand implements Command{

    @Override
    public void execute() {
        System.out.println("Completion of the program!");
        System.exit(0);
    }
}
