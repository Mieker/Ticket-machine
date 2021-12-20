package tickermachine.model;

public class Ticket {

    private String name;
    private double cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Ticket [name=" + name + ", cost=" + cost + "]";
    }

    public Ticket(double cost) {
        super();
        this.cost = cost;
    }

    public Ticket() {

    }

}
