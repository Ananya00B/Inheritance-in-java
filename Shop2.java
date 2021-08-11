package Inheritance.cls.work;

public class Shop2 {
	
	 protected String[] item_list;		// Goods list
	 protected double[] item_costs;     // Cost of each good
	 protected int[] item_stocks;       // Quantities available
	 private boolean is_available;	// To check if the good asked for, is in the list or not
	 
	 protected Shop2() {
		 //Default Constructor
	 }
	 
	 protected Shop2 (String[] items, double[] prices, int[] stocks) throws NoProperFormatException {
	     // Check if lists match; otherwise throw exception
		 if ((items.length != prices.length) || (items.length != stocks.length) || (prices.length != stocks.length)) {
			 System.out.println(new NoProperFormatException ());
		 }else {
			 item_list = new String[items.length];
		     item_costs = new double[prices.length];
		     item_stocks = new int[stocks.length];

		     for (int i=0; i<items.length; i++) {
		          item_list[i] = items[i];
		          item_costs[i] = prices[i];
		          item_stocks[i] = stocks[i];
		     }
		 }
	 }
	 	 
	 //Adding more to an existing item
	 protected void addItem(String item, int quant){
		 checkItem(item);
		 if(is_available) {
			 for(int i = 0; i < item_list.length; i++) {
				 if(item_list[i].equals(item)) {
					 item_stocks[i] += quant;
				 }
			}
		 }	 
	 }
	 
	//Setting Price of an existing item
	 protected void setPrice(String item, double price) {
		 checkItem(item);
		 if(is_available) {
			 for(int i = 0; i < item_list.length; i++) {
				 if(item_list[i].equals(item)) {
					 item_costs[i] = price;
				 }
			}
		 }			
	}

	//Method to buy an item
	 protected double buyItem (String itm, int qty){
		 try {
			 checkItem (itm);//Check the list of goods
			 for (int i=0; i<item_list.length; i++) {
				 if (item_list[i].equals (itm)){
                    if (item_stocks[i] >= qty) {
                        item_stocks[i] -= qty;
                        return (qty * item_costs[i]);
                    } else
                        System.out.println(new NoStockException (item_list[i], item_stocks[i]));
                }
            }			 
		 }catch(NoItemException e) {
			 System.out.println(e);
		 }
		 return 0;
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
}
