package Inheritance.cls.work;

import java.util.Scanner;

final public class FruitShop{
	
	private String[] item_list;		// Fruits list
	private double[] item_costs;     // Cost of each fruit
	private int[] item_stocks;       // Quantities available
	private boolean is_available;	// To check if the fruit asked for, is in the list or not
	
	//Default list of fruits with the prices and stocks available
	public FruitShop() {
		item_list = new String[]{"PAPAYA", "PINEAPPLE", "MELON", "ORANGE", "STRAWBERRY"};
		item_costs = new double[]{35.5, 50, 25.00, 40.00, 30.00};
		item_stocks = new int[] {11, 20, 10, 35, 15};
	}
	
	public FruitShop(String[] fruits, double[] prices, int[] stocks) throws NoProperFormatException {
		// Check if lists match; otherwise throws NoProperFormatException
		if ((fruits.length != prices.length) || (fruits.length != stocks.length) || (prices.length != stocks.length)) {
			 System.out.println(new NoProperFormatException ());
		 }else {
			 item_list = new String[fruits.length];
		     item_costs = new double[prices.length];
		     item_stocks = new int[stocks.length];

		     for (int i=0; i<fruits.length; i++) {
		          item_list[i] = fruits[i];
		          item_costs[i] = prices[i];
		          item_stocks[i] = stocks[i];
		     }
		 }
	}

	protected void listItem () {
        System.out.println("\nList of Available Fruits and their Prices :");
        System.out.printf("%5s %-30s %8s %5s\n", "SNo.", "Fruit", "Price", "Qty(killos).");
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
	
	public void addFruit(String fruit, int quant){
		 checkItem(fruit);
		 if(is_available) {
			 for(int i = 0; i < item_list.length; i++) {
				 if(item_list[i].equals(fruit)) {
					 item_stocks[i] += quant;
				 }
			}
		 }	 
	 }
	 
	 public void setPrice(String fruit, double price) {
		 checkItem(fruit);
		 if(is_available) {
			 for(int i = 0; i < item_list.length; i++) {
				 if(item_list[i].equals(fruit)) {
					 item_costs[i] = price;
				 }
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
		
	 
	//main method
	public static void main(String[] args) throws NoProperFormatException {
		String frt;
		double rate;
		int qnt;
		String[] fruit;
		int[] quant;
		double[] price;
		Scanner input = new Scanner(System.in);
		FruitShop shopowner = new FruitShop();
		shopowner.listItem();
		
		//Changing price of to an available fruit
		System.out.println("\nTo change the price of a fruit in the stock,");
		System.out.print("Enter Fruit name and Price: ");
		frt = input.next().toUpperCase();
		rate = input.nextDouble();
		shopowner.setPrice(frt, rate);
		shopowner.listItem();
		
		//Adding more to an existing stock
		System.out.println("\nTo add more to fruits in the stock,");
		System.out.print("Enter Fruit name and Quantity: ");
		frt = input.next().toUpperCase();
		qnt = input.nextInt();
		shopowner.addFruit(frt, qnt);
		shopowner.listItem();
		
		
		//To create a new stock
		System.out.println("\nNew stock,");
		fruit = new String[] {"LITCHI", "ORANGE", "MANGO", "GRAPES", "APPLE", "GUAVA", "PEARS"}; 
		quant = new int[]{40, 29, 35, 17, 25, 23, 12};
		price = new double[] {70.00, 50.00, 60.00, 40.00, 55.00, 35.00, 25.00};
		FruitShop owner1 = new FruitShop(fruit, price, quant);
		owner1.listItem();
		//Checking for a fruit
		System.out.print("\nEnter a fruit name to check if it's avavilable:");
		frt = input.next().toUpperCase();
		owner1.checkItem(frt);
		//Checking price for fruit
		owner1.getPrice("BANANA");//throws NoItemException
		owner1.getPrice("ORANGE");
		
		//To add a new stock in the shop, improper format
		fruit = new String[] {"LITCHI", "ORANGE", "MANGO", "GRAPES", "APPLE", "GUAVA"}; 
		quant = new int[]{40, 29, 35, 17, 25}; //Quantity is assigned only for 5, will give NoProperFormatException
		price = new double[] {70.00, 50.00, 60.00, 40.00, 55.00, 35.00};
		System.out.print("\nAnanya's fruits stock :");
		FruitShop ananya = new FruitShop(fruit, price, quant);
		
		/*
		 * This code will work fine once the list is formated properly
		 * 
		 * ananya.listItem();
		 * //Checking for a fruit
		 * System.out.print("\nEnter a fruit name to check if it's avavilable:");
		 * frt = input.next().toUpperCase();
		 * ananya.checkItem(frt);
		
		 * //Checking price for fruit
		 * ananya.getPrice("LITCHI");
		 * ananya.getPrice(frt);
		*/
	}
}
