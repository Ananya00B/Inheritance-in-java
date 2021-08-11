package Inheritance.cls.work;

public class NoProperFormatException extends Exception {
	private String explanation;

	public NoProperFormatException () {
        explanation = new String ("All the lists must be of the same size!");
    }
    
    public String toString () {
        return ("NoProperFormatException: " + explanation);
    }
}
