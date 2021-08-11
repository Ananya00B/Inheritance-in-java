package Inheritance.cls.work;

import java.util.Scanner;

final public class FruitShop2 extends Shop2{
	//Default list of fruits with the prices and stocks available
	public FruitShop2() {
		item_list = new String[]{"PAPAYA", "PINEAPPLE", "MELON", "ORANGE", "STRAWBERRY"};
		item_costs = new double[]{35.5, 50, 25.00, 40.00, 30.00};
		item_stocks = new int[] {11, 20, 10, 35, 15};
	}
	
	public FruitShop2(String[] fruits, double[] prices, int[] stocks) throws NoProperFormatException {
		super(fruits, prices, stocks);
	}

	protected void listItem () {
        System.out.println("\nList of Available Fruits and their Prices :");
        System.out.printf("%5s %-30s %8s %5s\n", "SNo.", "Fruit", "Price", "Qty(killos).");
        super.listItem();
	}
	
	
	//main method
	public static void main(String[] args) throws NoProperFormatException {
		String frt;
		int qnt;
		double rate;
		String[] fruit;
		int[] quant;
		double[] price;
		Scanner input = new Scanner(System.in);
		FruitShop2 shopowner = new FruitShop2();
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
		shopowner.addItem(frt, qnt);
		shopowner.listItem();
		
		//To create a new stock
		fruit = new String[] {"LITCHI", "ORANGE", "MANGO", "GRAPES", "APPLE", "GUAVA", "PEARS"}; 
		quant = new int[]{40, 29, 35, 17, 25, 23, 12};
		price = new double[] {70.00, 50.00, 60.00, 40.00, 55.00, 35.00, 25.00};
		FruitShop2 owner1 = new FruitShop2(fruit, price, quant);
		owner1.listItem();
		//Checking for a fruit
		System.out.print("\nEnter a fruit name to check if it's avavilable:");
		frt = input.next().toUpperCase();
		owner1.checkItem(frt);
		//Checking price for fruit
		owner1.getPrice("BANANA");
		owner1.getPrice("ORANGE");
		
		//To add a new stock in the shop, improper format
		fruit = new String[] {"LITCHI", "ORANGE", "MANGO", "GRAPES", "APPLE", "GUAVA"}; 
		quant = new int[]{40, 29, 35, 17, 25}; //Quantity is assigned only for 5, will give NoProperFormatException
		price = new double[] {70.00, 50.00, 60.00, 40.00, 55.00, 35.00};
		System.out.print("\nAnanya's fruits stock :");
		FruitShop2 ananya = new FruitShop2(fruit, price, quant);
		
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
