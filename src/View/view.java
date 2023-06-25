package View;

import java.util.ArrayList;
import java.util.List;

import Model.Inventory;
import Model.Kunde;
import Model.Order;

public class view {

	//private Kunde knd;
	//private Inventory in;
	//private Order o;
	//private List<Order> li = new ArrayList<>();

	//show all the customer
	public String showCustomer(List<Kunde> newList) {
		/*String tableFormat = "%-10s %-20s %-30s\n";
	    StringBuilder sb = new StringBuilder();

	    // Table header
	    sb.append(String.format(tableFormat, "ID", "Name", "Address"));
	    sb.append(String.format(tableFormat, "----------", "--------------------", "------------------------------"));
	    String header = sb.toString();
	    System.out.println(header);
		for(Kunde temp : newList) {
			System.out.println(temp.toString());
		}*/

		StringBuilder sb = new StringBuilder();
		String tableFormat = "%-15s %-20s %-20s\n";
		sb.append(String.format(tableFormat, "ID", "Name", "Address"));
		for (Kunde customer : newList) {
		    sb.append(String.format(tableFormat, "-----", "--------", "--------------------"));
		    sb.append(customer.toString()).append("\n");
		}
		return sb.toString();
	}
	//show all the Inventory
	public String showInventory(List<Inventory> newList) {

		String tableFormat = "%-10s %-30s %-10s\n";
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(tableFormat, "ID", "Product", "Quantity"));
		for(Inventory inventory : newList) {
			sb.append(String.format(tableFormat, "----------", "------------------------------", "----------"));
			sb.append(inventory.toString()).append("\n");
		}
		return sb.toString();
	}
	//show the orders in the queue
	public String showOrder(List<Order> newList) {
		String tableFormat = "%-10s %-30s %-10s %-10s\n";
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(tableFormat, "kunde", "adresse", "gekauft","Anzahl"));
		//sb.append(String.format(tableFormat, "----------", "------------------------------", "----------","----------"));
		for(Order orders : newList) {
			sb.append(String.format(tableFormat, "----------", "------------------------------", "----------","----------"));
			sb.append(orders.toString()).append("\n");
		}
		return sb.toString();
	}

	// show the costumer return stuff
	public void showRueckgabe(String Prod) {
		System.out.println("return Item:" + Prod);
	}


	//show the order after rueckgabe
	public void showOrderafterReturn() {}

}


