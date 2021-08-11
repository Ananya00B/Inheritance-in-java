package Inheritance.cls.work;

import java.util.InputMismatchException;
import java.util.Scanner;

final public class VegetableShop{
	private String[] item_list;         // Vegetable list
    private double[] item_costs;        // Cost of each vegetable
    private int[] item_stocks;          // Quantities available
    private boolean is_available;
    
	// Default initialization with a list of 5 vegetables,
    // their prices and stocks available
    public VegetableShop () {
    	item_list = new String[] {"ALOO", "BHINDI", "CABBAGE", "GOBI", "TOMATO"};
		item_costs = new double[] {30.00, 36.00, 24.00, 40.00, 20.00};
		item_stocks = new int[] {25, 10, 20, 10, 20};
    }
	
    // Constructor that initializes the list of available
	// vegetables, their prices and quantities.
	// Throws a NoProperFormatException in case of any mismatches in initialization
    public VegetableShop (String[] veglist, double[] prices, int[] stocks) throws NoProperFormatException {
    		// Check if lists match; otherwise throw exception
    	if ((veglist.length != prices.length) || (veglist.length != stocks.length) || (prices.length != stocks.length))
    		System.out.println(new NoProperFormatException ());
    	else {
    		item_list = new String[veglist.length];
			item_costs = new double[prices.length];
			item_stocks = new int[stocks.length];

			for (int i=0; i<veglist.length; i++) {
			item_list[i] = veglist[i];
			item_costs[i] = prices[i];
			item_stocks[i] = stocks[i];
			}
    	}
    }
    
  //Method to buy an item
  	 protected void buyItem (String itm, int qty){
  		 try {
  			 checkItem (itm);//Check the list of goods
  			 for (int i=0; i<item_list.length; i++) {
  				 if (item_list[i].equals (itm)){
                      if (item_stocks[i] >= qty) {
                          item_stocks[i] -= qty;
                          System.out.printf("Purchased %d Quant. of %s for Rs. %5.2f\n", qty, itm, (qty * item_costs[i]));
                          break;
                      } else
                          System.out.println(new NoStockException (item_list[i], item_stocks[i]));
                  }
              }			 
  		 }catch(NoItemException e) {
  			 System.out.println(e);
  		 }
  	 }
  		
  	 //Method to get the price of an available item
  	 protected void getPrice (String itm) {
  		 double c = 0.0;
  	     checkItem (itm);
  	     if(is_available) {
  	    	 for (int i=0; i<item_list.length; i++) {
  	        	 if (item_list[i].equals (itm)){
  	        		 c = item_costs[i];
  	        		 System.out.printf("Price for %s : %5.2f\n", itm, c);
  	        	 }
  	    	 }
  	  	 }
  	 }
  	 
  	 //Method to check if an item is available in the stock
  	 protected void checkItem(String itm) throws NoItemException{
  		 is_available = false;
  		 for (int i=0; i<item_list.length; i++) {
  			 if(item_list[i].equals(itm)) {
  					is_available = true;
  					System.out.printf("%s -- available\n", itm);
  					break;
  			 }
  		 }
  			
  		 if(!(is_available))
  			System.out.println(new NoItemException (itm));
  	 }
  	 
  	 //To list all the items available
  	 protected void listItem () {
  		System.out.println("\nList of Available Vegetables and their Prices :");
        System.out.printf("%5s %-30s %8s %5s\n", "SNo.", "Vegetable", "Price", "Qty(killos).");
  		 int flag = 1;
  	     for (int i=0; i<item_list.length; i++) {
  	         if (item_stocks[i] > 0 && flag == 1) {
  	        	 System.out.printf("%4d. %-30s %8.2f %3d\n", i+1, item_list[i], item_costs[i], item_stocks[i]);
  	          }else {
  	        	  if(flag == 1) {
  	        		  i++;
  	        		  flag = 0;
  	        	  }
  	             System.out.printf("%4d. %-30s %8.2f %3d\n", i, item_list[i], item_costs[i], item_stocks[i]);
  	          }
  	     }
  	 }
	 
	 public void addVegetable(String veg, int quant){
		 checkItem(veg);
		 if(is_available) {
			 for(int i = 0; i < item_list.length; i++) {
				 if(item_list[i].equals(veg)) {
					 item_stocks[i] += quant;
				 }
			}
		 }	 
	 }
	 
	 private void setPrice(String veg, double price) {
		 checkItem(veg);
		 if(is_available) {
			 for(int i = 0; i < item_list.length; i++) {
				 if(item_list[i].equals(veg)) {
					 item_costs[i] = price;
				 }
			}
		 }			
	}

	//main method 
	//To get the vegetables added by the shop owner
	public static void main(String[] args){
		int addveggie;
		String veggie;
		double price;
		Scanner input = new Scanner(System.in);
		VegetableShop vegowner1 = new VegetableShop();
		vegowner1.listItem();//Checking the list
		
		
		try {
			System.out.println("Enter a vegetable name to check if it's avavilable:");
			veggie = input.next().toUpperCase();
			vegowner1.checkItem(veggie);
		}catch(InputMismatchException e1) {		
			System.out.println(e1);
		}
		
		try {
			//Changing price of to an available vegetable
			System.out.println("\nTo change the price of a vegetables in the stock,");
			System.out.print("Enter Vegetable name and Price: ");
			veggie = input.next().toUpperCase();
			price = input.nextDouble();
			vegowner1.setPrice(veggie, price);
			vegowner1.listItem();
		}catch(InputMismatchException e1) {		
			System.out.println(e1);
		}
		
		try {
			//Adding more to an existing stock
			System.out.println("\nTo add more to vegetables in the stock,");
			System.out.print("Enter Vegetable name and Quantity: ");
			veggie = input.next().toUpperCase();
			addveggie = input.nextInt();
			vegowner1.addVegetable(veggie, addveggie);
			vegowner1.listItem();
		}catch(InputMismatchException e1) {		
			System.out.println(e1);
		}
	}

}
