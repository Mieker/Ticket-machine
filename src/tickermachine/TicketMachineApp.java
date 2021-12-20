package tickermachine;

import java.util.Map;

import tickermachine.model.Treasury;
import tickermachine.service.CashComputer;
import tickermachine.service.CashInputer;

public class TicketMachineApp {

    public static void main(String[] args) {

        boolean isTicketMachineOn = true;
        Treasury mainTreasury = new Treasury();

        supplyMainTreasury(mainTreasury);
        System.out.println("initial state of mainTreasury");
        System.out.println(mainTreasury.toString());

        while (isTicketMachineOn) {

            CashInputer cashInputer = new CashInputer();
            cashInputer.chooseTicket();
            double sumOfMoneyToGiveBack = cashInputer.insertCoins();

            CashComputer cashComputer = new CashComputer(sumOfMoneyToGiveBack, cashInputer.getInputedCoins(),
                    mainTreasury);

            if (cashComputer.isEnoughtMoneyToGiveRest()) {
                exchangeCash(cashComputer.getSetOfMoneyToPayBack(), mainTreasury);
                System.out.println("mainTreasury after pay of exchange");
                System.out.println(mainTreasury.toString());

                addCash(cashComputer.getInputedCoins(), mainTreasury);
                System.out.println("mainTreasury after adding coin");
                System.out.println(mainTreasury);
            }

        }

    }

    public static void addCash(Treasury cashToAdd, Treasury mainTreasury) {
        for (Map.Entry<Double, Integer> element : cashToAdd.getTreasuryOfCoins().entrySet()) {
            mainTreasury.addCoins(element.getKey(), element.getValue());
        }
    }

    public static void exchangeCash(Treasury cashToExchange, Treasury mainTreasury) {
        for (Map.Entry<Double, Integer> element : cashToExchange.getTreasuryOfCoins().entrySet()) {
            mainTreasury.removeCoins(element.getKey(), element.getValue());
        }
    }

    public static void supplyMainTreasury(Treasury mainTreasury) {
        mainTreasury.addCoins(0.10, 1);
        mainTreasury.addCoins(0.20, 10);
        mainTreasury.addCoins(0.50, 1);
        mainTreasury.addCoins(1, 20);
//        mainTreasury.addCoins(2, 14);
//        mainTreasury.addCoins(5, 3);
    }

}
