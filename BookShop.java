package lab.work.inheritance;

import java.util.Scanner;

import Inheritance.cls.work.NoItemException;
import Inheritance.cls.work.NoProperFormatException;
import Inheritance.cls.work.NoStockException;

public class BookShop extends Shop{
	private String authors_list[];
	
	//Default Constructor
	public BookShop() {
		item_list = new String[] {"Joyland", "Joyland", "Alice's Adventures in Wonderland", "The Book Thief"};
		authors_list = new String[] {"Emily Schultz", "Stephen King", "Lewis Carroll", "Markus Zusak"};
		item_stocks = new int[] {10, 15, 17, 20, 9};
		item_costs = new double[] {450, 900, 500, 350};
	}
	
	// Constructor that initializes the list of available books, their authors, prices and copies.
	// Throws a NoProperFormatException in case of any mismatches in initialization
	public BookShop (String[] books, String[] authors, double[] prices, int[] copies) throws NoProperFormatException{
		super(books, prices, copies);//calls for super class
		if (books.length != authors.length) {
			System.out.println(new NoProperFormatException ());
		}else {
			authors_list = new String[authors.length];
		     
		    for (int i=0; i<books.length; i++) {
		    	authors_list[i] = authors[i];
		    }
		 }
	}
	
	//To get the author name
	public void getAuthor(String book){
		checkItem(book);
		if(is_available) {
			for (int i=0; i<item_list.length; i++) {
	        	 if (item_list[i].equals (book)){
	        		 System.out.printf("Book : %s, Author : %s\n", book, authors_list[i]);
	        	 }
	    	}
		}
	}
	
	//To check the books if available
	//operator overloading
	public void checkItem(String book, String author){
		super.checkItem(book);
		if(is_available) {
			is_available = false;
			for (int i=0; i<item_list.length; i++) {
	        	 if (item_list[i].equals (book) && authors_list[i].equals (author)){
	        		 is_available = true;
	        		 System.out.printf("%s by %s-- available\n", book, author);
					 break;
	        	 }
	    	}
			
			if(!(is_available))
				System.out.println(new NoItemException (author));
		}
	}
	
	//Method to buy a book
	//operator overloading
	public double buyItem (String book, String author,  int qty){
		checkItem(book, author);
		if(is_available) {
			 for (int i=0; i<item_list.length; i++) {
				 if (item_list[i].equals (book) && authors_list[i].equals (author)){
					 if (item_stocks[i] >= qty) {
						 item_stocks[i] -= qty;
						 return (qty * item_costs[i]);
					 } else
                      System.out.println(new NoStockException (item_list[i], item_stocks[i]));
              }
          }			 
		 }
		 return 0;
	 }
	
	//Setting Price of an existing book
	//operator overloading
	protected void setPrice(String book, String author, double price) {
		 checkItem(book, author);
		 if(is_available) {
			 for(int i = 0; i < item_list.length; i++) {
				 if(item_list[i].equals(book) && authors_list[i].equals (author)) {
					 item_costs[i] = price;
					 System.out.println("Price has been updated.");
				 }
			}
		 }			
	}
	
	//Method to get the price of an available book
	//operator overriding
	public void getPrice (String book) {
		 double c = 0.0;
	     checkItem (book);
	     if(is_available) {
	    	 for (int i=0; i<item_list.length; i++) {
	        	 if (item_list[i].equals (book)){
	        		 c = item_costs[i];
	        		 System.out.printf("Price for %s by %s: %5.2f\n", book, authors_list[i], c);
	        	 }
	    	 }
	  	 }
	 }
	
	//To list all the books available
	//operator overriding
	public void listItem () {
		System.out.println("\nList of Available Books, their Authors and Prices :");
	    System.out.printf("%5s %-30s\t%-30s\t%8s\t%5s\n", "SNo.", "Book", "Author", "Price", "Stock.");
		int flag = 1;
	    for (int i=0; i<item_list.length; i++) {
	        if (item_stocks[i] > 0 && flag == 1) {
	        	System.out.printf("%4d. %-30s\t%-30s\t%8.2f\t%3d\n", i+1, item_list[i], authors_list[i], item_costs[i], item_stocks[i]);
	        }else {
	        	if(flag == 1) {
	        		 i++;
	        		 flag = 0;
	        	}
	        	System.out.printf("%4d. %-30s\t%-30s\t%8.2f\t%3d\n", i+1, item_list[i], authors_list[i], item_costs[i], item_stocks[i]);
	        }
	     }
	 }
	
	//To add more to an existing book
	protected void addBook(String book, String author, int copies) {
		checkItem(book, author);
		if(is_available) {
			 for(int i = 0; i < item_list.length; i++) {
				 if(item_list[i].equals(book) && authors_list[i].equals(author)) {
					 item_stocks[i] += copies;
					 System.out.println("Copies has been added.");
					 break;
				 }
			}
		 }
	}
	
	//main method
	public static void main(String[] args) throws NoProperFormatException {
		BookShop owner1 = new BookShop();
		owner1.listItem();//Listing available books
		
		int copy;
		String book;
		String author;
		double price;
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a Book name to check if it's avavilable : ");
		book = input.nextLine();
		owner1.checkItem(book);
		
		System.out.println("\nInsert a Book and Author name to check if it's avavilable, ");
		System.out.print("Enter Book name: ");
		book = input.nextLine();
		System.out.print("Enter Author Name: ");
		author = input.nextLine();
		owner1.checkItem(book, author);//Checking for a book
		
		//To check the author of the book
		System.out.print("\nEnter a Book name to get the Author name : ");
		book = input.nextLine();
		owner1.getAuthor(book);
		//To get price of the book
		System.out.println("\nPrice for the above book : ");
		owner1.getPrice(book);
		
		//Adding more to an existing book stock
		System.out.println("\nTo add more to Book in the stock,");
		System.out.print("Enter Book name: ");
		book = input.nextLine();
		System.out.print("Enter Author Name: ");
		author = input.nextLine();
		System.out.print("Enter no. of Copies: ");
		copy = Integer.parseInt(input.nextLine());
		owner1.addBook(book, author, copy);
		owner1.listItem(); //Listing books again
				
		//Changing price of to an available vegetable
		System.out.println("\nTo change the price for a Book in the stock,");
		System.out.print("Enter Book name: ");
		book = input.nextLine();
		System.out.print("Enter Author Name: ");
		author = input.nextLine();
		System.out.print("Enter Price: ");
		price = Double.parseDouble(input.nextLine());
		owner1.setPrice(book, author, price);
		owner1.listItem();//Listing books again
		
		//Creating a new stock
		String books[] = {"The Alchemist", "Becoming", "Contemporary Abstract Algebra", "Cryptography and Network Security"};
		String authors[] = {"Paulo Coelho", "Michelle Obama", "Joseph Gallian", "William Stallings"};
		double prices[] = {300, 900, 600, 700};
		int copies[] = {18, 10, 20, 15};
		System.out.print("\nCreating a new stock :");
		BookShop owner2 = new BookShop(books, authors, prices, copies);
		owner2.listItem();
	}

}
