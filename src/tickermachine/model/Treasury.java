package tickermachine.model;

import java.util.Map;
import java.util.TreeMap;

public class Treasury {

    private Map<Double, Integer> treasuryOfCoins = new TreeMap<>();

    public Treasury() {

        treasuryOfCoins.put(0.10, 0);
        treasuryOfCoins.put(0.20, 0);
        treasuryOfCoins.put(0.50, 0);
        treasuryOfCoins.put(1.00, 0);
        treasuryOfCoins.put(2.00, 0);
        treasuryOfCoins.put(5.00, 0);

    }

    public void addCoins(double coinValue, int coinsToAdd) {

        for (Map.Entry<Double, Integer> entry : this.treasuryOfCoins.entrySet()) {

            if (entry.getKey() == coinValue) {
                entry.setValue(entry.getValue() + coinsToAdd);
            }

        }

    }

    public void removeCoins(double coinValue, int coinsToRemove) {

        for (Map.Entry<Double, Integer> entry : this.treasuryOfCoins.entrySet()) {

            if (entry.getKey() == coinValue) {

                if (entry.getValue() >= coinsToRemove) {
                    entry.setValue(entry.getValue() - coinsToRemove);
                } else {
                    System.out.println("Not enough coins to pay.");
                }
            }
        }

    }

    public String toString() {

        String treasuryContent = "";
        for (Map.Entry<Double, Integer> entry : this.treasuryOfCoins.entrySet()) {
            treasuryContent = treasuryContent + entry.getKey() + " - " + entry.getValue() + "\n";

        }
        return treasuryContent;
    }

    public Map<Double, Integer> getTreasuryOfCoins() {
        return treasuryOfCoins;
    }

    public void setTreasuryOfCoins(Map<Double, Integer> treasuryOfCoins) {
        this.treasuryOfCoins = treasuryOfCoins;
    }

    public double countAllMoney() {

        double sumOfMoney = 0.00;

        for (Map.Entry<Double, Integer> element : treasuryOfCoins.entrySet()) {
            sumOfMoney += (element.getKey() * element.getValue());
        }
        return sumOfMoney;
    }
}
