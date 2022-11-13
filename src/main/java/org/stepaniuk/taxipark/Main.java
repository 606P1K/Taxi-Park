package org.stepaniuk.taxipark;

import org.stepaniuk.taxipark.Menu.OwnerInterface;

public class Main {
    public static void main(String[] args) {
        OwnerInterface ownerInterface = new OwnerInterface();
        ownerInterface.showMenu();
    }
}