package tickermachine.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import tickermachine.model.Treasury;

public class CashComputer {

    double sumOfMoneyToGiveBack;
    Treasury inputedCoins;
    Treasury coinsAvailable;
    Map<Double, Integer> coinsAvailableInReverseOrder;
    Treasury setOfMoneyToPayBack = new Treasury();

    public CashComputer(double sumOfMoneyToGiveBack, Treasury inputedCoins, Treasury coinsAvailable) {
        super();
        this.sumOfMoneyToGiveBack = sumOfMoneyToGiveBack;
        this.inputedCoins = inputedCoins;
        this.coinsAvailable = coinsAvailable;
    }

    private void reverseCoinsAvailable() {
        coinsAvailableInReverseOrder = new TreeMap<Double, Integer>(Collections.reverseOrder());

        for (Map.Entry<Double, Integer> element : coinsAvailable.getTreasuryOfCoins().entrySet()) {
            coinsAvailableInReverseOrder.put(element.getKey(), element.getValue());
        }
    }

    public boolean isEnoughtMoneyToGiveRest() {

        reverseCoinsAvailable();

        double temporarySumOfMoneyToGiveBack = sumOfMoneyToGiveBack;

        while (temporarySumOfMoneyToGiveBack != 0.00) {

            for (Map.Entry<Double, Integer> element : coinsAvailableInReverseOrder.entrySet()) {
                if (element.getValue() != 0 && element.getKey() <= temporarySumOfMoneyToGiveBack) {
                    setOfMoneyToPayBack.addCoins(element.getKey(), 1);
                    coinsAvailableInReverseOrder.replace(element.getKey(), element.getValue() - 1);
                    temporarySumOfMoneyToGiveBack = round(temporarySumOfMoneyToGiveBack - element.getKey());
                    break;
                }
                if (element.getKey() == 0.10 && element.getValue() == 0 && temporarySumOfMoneyToGiveBack > 0) {
                    System.out.println("Not enough money to pay back!!");
                    return false;
                }
            }
        }

        System.out.println("available coins");
        System.out.println(coinsAvailable.toString());
        System.out.println("set of money to pay back");
        System.out.println(setOfMoneyToPayBack);

        return true;

    }

    public Treasury getInputedCoins() {
        return inputedCoins;
    }

    public Treasury getSetOfMoneyToPayBack() {
        return setOfMoneyToPayBack;
    }

    public static double round(double value) {
        int precision = 2;
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(precision, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

}
