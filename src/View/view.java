package View;

import java.util.ArrayList;
import java.util.List;

import Model.Inventory;
import Model.Kunde;
import Model.Order;

public class view {

	//show all the customer
	public String showCustomer(List<Kunde> newList) {
		StringBuilder sb = new StringBuilder();
		String tableFormat = "%-15s %-20s %-20s\n";
		sb.append(String.format(tableFormat, "ID", "Name", "Address"));
		for (Kunde customer : newList) {
		    sb.append(customer.toString()).append("\n");
		}
		return sb.toString();
	}
	
	//show the Inventory 
	public String showInventory(List<Inventory> newList) {
	    StringBuilder sb = new StringBuilder();
	    String tableFormat = "%-10s %-30s %-10s\n";
	    sb.append(String.format(tableFormat, "ID", "Product", "Quantity"));

	    for (Inventory inventory : newList) {
	        sb.append(inventory.toString()).append("\n");
	    }

	    return sb.toString();
	}

	//show the orders in the queue
	public String showOrder(List<Order> newList) {
		String tableFormat = "%-10s %-60s %-10s %-10s\n";
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(tableFormat, "kunde", "Adresse", "gekauft","Anzahl"));
		for(Order orders : newList) {
			sb.append(orders.toString()).append("\n");
		}
		return sb.toString();
	}

	// show the costumer return stuff
	public void showRueckgabe(String Prod) {
		System.out.println("return Item:" + Prod);
	}



}


