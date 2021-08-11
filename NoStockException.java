package Inheritance.cls.work;

public class NoStockException extends ArithmeticException {
    private String explanation;

    public NoStockException () {
        explanation = new String ("Insufficient Stocks!");
    }

    public NoStockException (int qty) {
        explanation = new String ("Only " + qty + " Kilos Available");
    }

    public NoStockException (String itm, int qty) {
        explanation = new String (itm + "-- Only " + qty + " Kilos Available");
    }

    public String toString () {
        return ("NoStockException: " + explanation);
    }
}
