package tickermachine.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import tickermachine.model.Ticket;
import tickermachine.model.Treasury;

public class CashInputer {

    private Scanner ticketScanner = new Scanner(System.in);
    private Scanner coinScanner = new Scanner(System.in);
    private Ticket ticket = new Ticket();
    private Treasury inputedCoins = new Treasury();

    public void chooseTicket() {
        System.out.println("Wybierz bilet:");
        System.out.println("1. Ulgowy 20 min");
        System.out.println("2. Ulgowy 40 min");
        System.out.println("3. Normalny 20 min");
        System.out.println("4. Normalny 40 min");

        while (!ticketScanner.hasNextInt()) {
            System.out.println("Wrong value, try again!");
            ticketScanner.next();
        }
        int choosenTicket = ticketScanner.nextInt();
        while (choosenTicket <= 0 || choosenTicket > 4) {
            System.out.println("Wrong value, try again!");

            choosenTicket = ticketScanner.nextInt();
        }

        switch (choosenTicket) {
        case 1:
            ticket.setCost(1.20);
            ticket.setName("Ulgowy 20 min");
            break;
        case 2:
            ticket.setCost(2.00);
            ticket.setName("Ulgowy 40 min");
            break;
        case 3:
            ticket.setCost(2.40);
            ticket.setName("Normalny 20 min");
            break;
        case 4:
            ticket.setCost(4.00);
            ticket.setName("Normalny 40 min");
            break;

        default:
            break;
        }

        System.out.println("Wybrano bilet:" + ticket.toString());
    }

    public double insertCoins() {

        double moneyToGiveBack = 0;

        while (inputedCoins.countAllMoney() < ticket.getCost()) {

            System.out.println("Włóż monetę:");
            System.out.println("1. 0.10 zł");
            System.out.println("2. 0.20 zł");
            System.out.println("3. 0.50 zł");
            System.out.println("4. 1.00 zł");
            System.out.println("5. 2.00 zł");
            System.out.println("6. 5.00 zł");

            while (!coinScanner.hasNextInt()) {
                System.out.println("Wrong value, try again!");
                coinScanner.next();
            }
            int inputedCoinNumber = coinScanner.nextInt();
            while (inputedCoinNumber <= 0 || inputedCoinNumber > 6) {
                System.out.println("Wrong value, try again!");

                inputedCoinNumber = coinScanner.nextInt();
            }
            
            double lastCoin = 0.0;

            switch (inputedCoinNumber) {
            case 1:
                inputedCoins.addCoins(0.10, 1);
                lastCoin = 0.10;
                break;
            case 2:
                inputedCoins.addCoins(0.20, 1);
                lastCoin = 0.20;
                break;
            case 3:
                inputedCoins.addCoins(0.50, 1);
                lastCoin = 0.50;
                break;
            case 4:
                inputedCoins.addCoins(1.00, 1);
                lastCoin = 1.00;
                break;
            case 5:
                inputedCoins.addCoins(2.00, 1);
                lastCoin = 2.00;
                break;
            case 6:
                inputedCoins.addCoins(5.00, 1);
                lastCoin = 5.00;
                break;

            default:
                break;
            }
            System.out.println("Wrzucono " + lastCoin + " zł");

            if (ticket.getCost() >= inputedCoins.countAllMoney()) {
                System.out.println("Pozostało do zapłaty:");
                System.out.println(round(ticket.getCost() - inputedCoins.countAllMoney()) + " zł");
            } else {
                System.out.println("Do zwrotu: ");
                moneyToGiveBack = Math.abs(round(ticket.getCost() - inputedCoins.countAllMoney()));
                System.out.println(moneyToGiveBack + " zł");
            }
        }

        return moneyToGiveBack;
    }

    public Treasury getInputedCoins() {
        return inputedCoins;
    }

    public void setInputedCoins(Treasury inputedCoins) {
        this.inputedCoins = inputedCoins;
    }

    public static double round(double value) {
        int precision = 2;
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(precision, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
