package Inheritance.cls.work;

public class NoItemException extends ArithmeticException {
    private String explanation;

    public NoItemException () {
        explanation = new String ("Specified Item Not Found!");
    }

    public NoItemException (String it) {
        explanation = new String (it + "-- Not in Items List");
    }
    
    public String toString () {
        return ("NoItemException: " + explanation);
    }
}
