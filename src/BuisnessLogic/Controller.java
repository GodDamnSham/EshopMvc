package BuisnessLogic;

import java.util.ArrayList;
import java.util.List;

import Model.Inventory;
import Model.Kunde;
import Model.Order;
import View.view;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class Controller extends JFrame{
	private List<Kunde> kundenList;
	private List<Inventory> invtList;
	private List<Order> orderList;
	private view v;

	public Controller(view _v) {
		this.v = _v;
		this.kundenList = new ArrayList<>();
		this.invtList = new ArrayList<>();
		this.orderList = new ArrayList<>();
	}


	public Controller(Order o, view _v) {
		this.v = _v;
		this.orderList = new ArrayList<>();
		this.orderList.add(o);
	}


	// Methode um die Bestellung hinzufügen 
	public void orderHinzufuegen(Order newOrder, int Anzahl) {
		if(!this.orderAnzahlAnpassen(newOrder, Anzahl)) {
			newOrder.setAnzahl(Anzahl);
			this.orderList.add(newOrder);
		}
		this.inventoryAnzahlVerringern(newOrder.getI().getId(), Anzahl);
		/*tempAnzahl = tempAnzahl - Anzahl;
		for(int i=0; i<this.invtList.size() ; i++) {
			if(this.getInvtList().get(i).getProduct() == newOrder.getI().getProduct()) {
				this.invtList.get(i).setQuantity(tempAnzahl);
			}
		}*/
		//this.inventoryAnzahlVerringern(newOrder.getI().getId(), Anzahl);
		//this.inventoryAnzahlVerringern(newOrder.getI(), Anzahl);
		/*Inventory inventory = findInventoryByProduct(newOrder.getI().getProduct());
		if (inventory != null) {
			increaseProductQuantity(inventory, Anzahl);
		} else {
			System.out.println("Product not found in the inventory.");
		}*/
	}

	//if the user add a new order but there is a same order so it will adjust the product quantity
	public boolean orderAnzahlAnpassen(Order o , int Anzahl) {
		for (Order tempOrder : this.getOrderList()) {
			if(tempOrder.getK().getName().contains(o.getK().getName()) && tempOrder.getI().getId() == o.getI().getId()) {
				tempOrder.getI().setQuantity(tempOrder.getI().getQuantity() + Anzahl);
				return true;
			}

		}
		return false;
	}
	// Method use to decrese the amount of product in Inventar
	public boolean inventoryAnzahlVerringern(int id , int Anzahl) {
		//this.invtList.get(i.getId()).setQuantity(this.invtList.get(i.getId()).getQuantity() - Anzahl);
		for (Inventory tempInventory : this.getInvtList()) {
			if(tempInventory.getId() == id) {
				tempInventory.setQuantity(tempInventory.getQuantity() - Anzahl);
				return true;
			}

		}
		return false;
	}


	// method to delete a specific amount of Product in order 
	public void orderLoeschenMitAnzahl(String product , int Anzahl , String Kunde) {
		Order orderToDelete = this.findOrderByNameAndKunde(product, Kunde);
		if (orderToDelete != null) {
			this.KundeInventoryAnzahlVerringern(orderToDelete.getK().getName() ,orderToDelete.getI().getProduct(), Anzahl);
			//orderList.remove(orderToDelete);
			System.out.println("Order deleted successfully.");
			this.showReturnProduct(orderToDelete.getI().getProduct());
			inventoryAnzahlErhöhen(orderToDelete.getI().getId(), Anzahl);
		} else {
			System.out.println("Order not found.");
		}
	}

	//method to delete complete order
	public void orderLoeschen(String product , String Kunde) {
		Order orderToDelete = this.findOrderByNameAndKunde(product, Kunde);
		if (orderToDelete != null) {
			orderList.remove(orderToDelete);
			System.out.println("Order deleted successfully.");
			this.showReturnProduct(orderToDelete.getI().getProduct());
			inventoryAnzahlErhöhen(orderToDelete.getI().getId(), orderToDelete.getAnzahl());
		} else {
			System.out.println("Order not found.");
		}
	}

	//kunden inventory Anzahl anpassen
	public boolean KundeInventoryAnzahlVerringern(String name , String product, int Anzahl) {
		for (Order temp : this.getOrderList()) {
			if(temp.getK().getName() == name && temp.getI().getProduct() == product) {
				temp.getI().setQuantity(temp.getI().getQuantity() - Anzahl);
				return true;
			}

		}
		return false;
	}

	// method to increase the amount of product in the inventar
	public boolean inventoryAnzahlErhöhen(int id , int Anzahl) {
		for (Inventory tempInventory : this.getInvtList()) {
			if(tempInventory.getId() == id) {
				tempInventory.setQuantity(tempInventory.getQuantity() + Anzahl);
				return true;
			}

		}
		return false;
	}

	//helping method 
	public void reduceProductQuantity(Inventory inventory, int quantity) {
		for (Inventory item : this.getInvtList()) {
			if (item.equals(inventory)) {
				int currentQuantity = item.getQuantity();
				int newQuantity = currentQuantity - quantity;
				if (newQuantity >= 0) {
					item.setQuantity(newQuantity);
					System.out.println("Product quantity reduced successfully.");
				} else {
					System.out.println("Product out of stock!");
				}
				return;
			}
		}
		System.out.println("Product not found in the inventory.");
	}

	//helping method 
	public void increaseProductQuantity(Inventory inventory, int quantity) {
		for (Inventory item : this.getInvtList()) {
			if (item.equals(inventory)) {
				int currentQuantity = item.getQuantity();
				int newQuantity = currentQuantity + quantity;
				item.setQuantity(newQuantity);
				System.out.println("Product quantity increased successfully.");
				return;
			}
		}
		System.out.println("Product not found in the inventory.");
	}
	
	//helping method returning the product quantity 
	public int returnProductQuantity(Inventory p) {
		int temp = 0;
		for(Inventory temp1  : this.invtList) {
			if(temp1.getId() == p.getId()) {
				temp = temp1.getQuantity();
			}
		}
		return temp;
	}
	// Add costumer
	public void kundeHinzufuegen(Kunde kunde) {
		kundenList.add(kunde);
	}

	// add product 
	public void invtHinzufuegen(Inventory invt) {
		invtList.add(invt);
	}

	//this will give an order based on the costumer name and the product name
	public Order findOrderByNameAndKunde(String name , String kunde ) {
		for (Order order : this.getOrderList()) {
			if (order.getI().getProduct().equals(name) && order.getK().getName().equals(kunde)) {
				return order;
			}
		}
		return null; 
	}

	// find a costumer with his name
	public Kunde findCustomerByName(String name) {
		for (Kunde customer : this.getKundenList()) {
			if (customer.getName().equals(name)) {
				return customer;
			}
		}
		return null; 
	}

	// find a product by Id 
	public Inventory findProductById(int productId) {
		for (Inventory inventory : this.invtList) {
			if (inventory.getId() == productId) {
				return inventory;
			}
		}
		return null; 
	}
	// find product by name
	public Inventory findProductByName(String name) {
		for (Inventory inventory : this.getInvtList()) {
			if (inventory.getProduct().equals(name)) {
				return inventory;
			}
		}
		return null; 
	}


	/*
	private Order findOrderByName(String Name) {
	    for (Order order : this.getOrderList()) {
	        if (order.getI().getProduct().equals(Name)) {
	            return order;
	        }
	    }
	    return null; 
	}


	private Order findOrderByInventory(Inventory inventory) {
		for (Order order : orderList) {
			if (order.getI().equals(inventory)) {
				return order;
			}
		}
		return null;
	}

	private Inventory findInventoryByProduct(String product) {
		for (Inventory item : invtList) {
			if (item.getProduct().equals(product)) {
				return item;
			}
		}
		return null;
	}*/
	// showing return Items 
	public void showReturnProduct(String Ptoduct) {
		v.showRueckgabe(Ptoduct);
	}

	// show the costumer
	public String UpdateKunde() {
		String updatedKundenList = v.showCustomer(this.kundenList);
		return updatedKundenList;
	}
	//show the Inventar 
	public String updateInventory() {
		String updatedInventoryList = v.showInventory(this.invtList);
		return updatedInventoryList;
	}
	//show the order after update
	public String updateOrder() {
		String upDatedOrderList = v.showOrder(this.orderList);
		return upDatedOrderList;
	}

	/*
	 * Getter and setters
	 */
	public List<Kunde> getKundenList() {
		return kundenList;
	}


	public void setKundenList(List<Kunde> kundenList) {
		this.kundenList = kundenList;
	}


	public List<Inventory> getInvtList() {
		return this.invtList;
	}


	public void setInvtList(List<Inventory> invtList) {
		this.invtList = invtList;
	}


	public List<Order> getOrderList() {
		return this.orderList;
	}


	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
}