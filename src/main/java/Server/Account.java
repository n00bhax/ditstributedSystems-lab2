package Server;

public class Account {

    private final String name;
    private double balance = 0;

    public Account(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public synchronized void changeBalance(double change) {
        this.balance += change;
    }
}
