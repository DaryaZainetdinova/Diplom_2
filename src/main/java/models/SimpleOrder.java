package models;

public class SimpleOrder {
    public int number;

    public SimpleOrder(int number) {
        this.number = number;
    }

    public SimpleOrder() {}

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
