package Inheritance.cls.work;

import java.util.*;

public class ShopMain {
    public static void main (String[] args){
        Scanner input = new Scanner(System.in);
        String veg, frt;
        int qt;
        try {
            String[] veggies = new String[] {"ARBI", "ALOO", "BHINDI",
                                             "CAPSICUM", "DONDAKAYA", "TURAI",
                                             "KARELA"};
            double[] costs = new double[] {60.00, 30.00, 24.00,
                                           60.00, 40.00, 80.00,
                                           36.00};
            int[] quants = new int[] {12, 30, 16, 15, 20, 12, 10};
            
            //For vegetable shop
            //Creating a new stock
            //buying a vegetable from the list and updating the list
            //Also checking if a particular item is there or not
            VegetableShop reddy = new VegetableShop (veggies, costs, quants);
            System.out.println("Reddy's Vegetable Shop :");
            reddy.listItem();
            System.out.print("Enter Vegetable Name and Qty to buy: ");
            veg = input.next().toUpperCase();
            qt = input.nextInt(); 
        	reddy.buyItem(veg, qt);
            reddy.listItem();
            reddy.checkItem("BEANS");
            reddy.getPrice("CAPSICUM");
            reddy.getPrice("MUSHROOM");
            
            //Listing the fruits from the default list
            //Buying a fruit, updating the list after buying
            //Checking if a particular fruit is available or not.
            //Checking for the price of particular fruits
            FruitShop pradeep = new FruitShop ();
    		System.out.println("\nPradeep's Fruit Shop :");
    		pradeep.listItem();
    		System.out.print("Enter Fruit Name and Qty to buy: ");
    		frt = input.next().toUpperCase();
    		qt = input.nextInt();
    		pradeep.buyItem(frt, qt);
    		pradeep.listItem();
    		pradeep.checkItem("MANGO");
    		pradeep.getPrice("PAPAYA");
    		pradeep.getPrice("BANANA");
		}catch (NoProperFormatException e1) {
			System.out.println(e1);
		}
    }
}
