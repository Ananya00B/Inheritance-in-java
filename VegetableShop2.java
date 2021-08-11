package Inheritance.cls.work;

import java.util.Scanner;

final public class VegetableShop2 extends Shop2{
	// Default initialization with a list of 5 vegetables,
    // their prices and stocks available
    public VegetableShop2 () {
    	item_list = new String[] {"ALOO", "BHINDI", "CABBAGE", "GOBI", "TOMATO"};
		item_costs = new double[] {30.00, 36.00, 24.00, 40.00, 20.00};
		item_stocks = new int[] {25, 10, 20, 10, 20};
    }
	
    // Constructor that initializes the list of available
	// vegetables, their prices and quantities.
	// Throws a NoProperFormatException in case of any mismatches in initialization
	 protected VegetableShop2 (String[] veglist, double[] prices, int[] stocks) throws NoProperFormatException{
	     // Check if lists match; otherwise throw exception
		 super(veglist, prices, stocks);
	 }
	 
	protected void listItem () {
	        System.out.println("\nList of Available Vegetables and their Prices :");
	        System.out.printf("%5s %-30s %8s %5s\n", "SNo.", "Vegetable", "Price", "Qty(killos).");
	        super.listItem();
	}
	
	/*
	//Adding more to an existing item
	private void addItem(String item, int quant){
		super.addItem(item, quant);	 
	}
	*/
	
	//main method 
	//To get the vegetables added by the shop owner
	public static void main(String[] args) throws NoProperFormatException {
		int addveggie;
		String veggie;
		double price;
		Scanner input = new Scanner(System.in);
		VegetableShop2 vegowner1 = new VegetableShop2();
		vegowner1.listItem();
		System.out.println("Enter a vegetable name to check if it's avavilable");
		veggie = input.next().toUpperCase();
		vegowner1.checkItem(veggie);//Checking for a vegetable
		
		//Changing price of to an available vegetable
		System.out.println("\nTo change the price of a vegetables in the stock,");
		System.out.print("Enter Vegetable name and Price: ");
		veggie = input.next().toUpperCase();
		price = input.nextDouble();
		vegowner1.setPrice(veggie, price);
		vegowner1.listItem();
		
		//Adding more to an existing stock
		System.out.println("\nTo add more to vegetables in the stock,");
		System.out.print("Enter Vegetable name and Quantity: ");
		veggie = input.next().toUpperCase();
		addveggie = input.nextInt();
		vegowner1.addItem(veggie, addveggie);
		vegowner1.listItem();
		
		
		//Creating a new stock by user input
		System.out.println("\nTo create a new vegetable stock,");
		System.out.println("How many vegetables you wanna add in stock?");
		addveggie = input.nextInt();
		
		String[] veg1 = new String[addveggie];
		double[] price1 = new double[addveggie];
		int[] quant1 = new int[addveggie];
		for(int i = 0; i < addveggie; i++) {
			System.out.printf("%d) Enter Vegetable name : ", i+1);
			veg1[i] = input.next().toUpperCase();
			System.out.print("Enter Quantity : ");
			quant1[i] = input.nextInt();
			System.out.print("Enter price : ");
			price1[i] = input.nextDouble();
		}
		VegetableShop2 vegowner2 = new VegetableShop2(veg1, price1, quant1);
		vegowner2.listItem();
		//Adding more to an existing stock in the new stock
		System.out.println("Adding more to an existing vegetable,");
		System.out.print("Enter Vegetable name and Quantity: ");
		veggie = input.next().toUpperCase();
		addveggie = input.nextInt();
		vegowner2.addItem(veggie, addveggie);
		vegowner2.listItem();
		//Changing price of to an available vegetable in the new stock
		System.out.println("\nTo change the price of a vegetables in the stock,");
		System.out.print("Enter Vegetable name and Price: ");
		veggie = input.next().toUpperCase();
		price = input.nextDouble();
		vegowner2.setPrice(veggie, price);
		vegowner2.listItem();
	}
	
}
