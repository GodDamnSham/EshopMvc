package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.Inventory;
import Model.Kunde;
import Model.Order;
import View.view;

public class Controller {
	private List<Kunde> kundenList;
	private List<Inventory> invtList;
	private List<Order> orderList;
/*
 * changes to be made : user should be asked how much he want to delet from the given order  done::
 * or if he wants to delete the whole order 
 * the table string problem in UI.. 
 * the login UI problem.
 */


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

	// order -> ivent

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
	/*
	 * ask the user for the name check the name in the order list --- note the name   use method findOrderByName
	 * ask the user which product he wants to delete and how many units of this product 
	 * go to the order list -> check if person there , if it is there then 
	 * check of the product is there and then decrese the product count and add this product count in the inventory. 
	 */
	
	//if the user add a new order but there is a same order so it will adjust the product quantity
	public boolean orderAnzahlAnpassen(Order o , int Anzahl) {
		for (Order tempOrder : this.orderList) {
			if(tempOrder.getK().getName().contains(o.getK().getName()) && tempOrder.getI().getId() == o.getI().getId()) {
				tempOrder.getI().setQuantity(tempOrder.getI().getQuantity() + Anzahl);
				//this.inventoryAnzahlVerringern(tempOrder.getI(), Anzahl);
				return true;
			}
			
		}
		return false;
	}
	
	public boolean inventoryAnzahlVerringern(int id , int Anzahl) {
		//this.invtList.get(i.getId()).setQuantity(this.invtList.get(i.getId()).getQuantity() - Anzahl);
		for (Inventory tempInventory : this.invtList) {
			if(tempInventory.getId() == id) {
				tempInventory.setQuantity(tempInventory.getQuantity() - Anzahl);
				return true;
			}
			
		}
		return false;
	}
	
	
	// method to delete a specific amount of order 
	public void orderLoeschenMitAnzahl(String product , int Anzahl) {
		Order orderToDelete = this.findOrderByName(product);
		if (orderToDelete != null) {
			this.KundeInventoryAnzahlVerringern(orderToDelete.getI().getId(), Anzahl);
			//orderList.remove(orderToDelete);
			System.out.println("Order deleted successfully.");
			this.showReturnProduct(orderToDelete.getI().getProduct());
			inventoryAnzahlErhöhen(orderToDelete.getI().getId(), Anzahl);
		} else {
			System.out.println("Order not found.");
		}
	}

	//method to delete complete order
	public void orderLoeschen(String product) {
		Order orderToDelete = this.findOrderByName(product);
		if (orderToDelete != null) {
			orderList.remove(orderToDelete);
			System.out.println("Order deleted successfully.");
			this.showReturnProduct(orderToDelete.getI().getProduct());
			inventoryAnzahlErhöhen(orderToDelete.getI().getId(), orderToDelete.getAnzahl());
		} else {
			System.out.println("Order not found.");
		}
	}


	public boolean KundeInventoryAnzahlVerringern(int id , int Anzahl) {
		//this.invtList.get(i.getId()).setQuantity(this.invtList.get(i.getId()).getQuantity() - Anzahl);
		for (Order tempInventory : this.orderList) {
			if(tempInventory.getI().getId() == id) {
				tempInventory.getI().setQuantity(tempInventory.getI().getQuantity() - Anzahl);
				return true;
			}
			
		}
		return false;
	}
	
	public boolean inventoryAnzahlErhöhen(int id , int Anzahl) {
		//this.invtList.get(i.getId()).setQuantity(this.invtList.get(i.getId()).getQuantity() - Anzahl);
		for (Inventory tempInventory : this.invtList) {
			if(tempInventory.getId() == id) {
				tempInventory.setQuantity(tempInventory.getQuantity() + Anzahl);
				return true;
			}
			
		}
		return false;
	}
	
	//helping method 
	public void reduceProductQuantity(Inventory inventory, int quantity) {
		for (Inventory item : invtList) {
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
		for (Inventory item : invtList) {
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


	public void kundeHinzufuegen(Kunde kunde) {
		kundenList.add(kunde);
	}

	public void invtHinzufuegen(Inventory invt) {
		/*for (Inventory item : this.invtList) {
			if (item.getProduct().equals(invt.getProduct())) {
				this.inventoryAnzahlErhöhen(item.getId(), productAnzahl);
				//item.setQuantity(item.getQuantity() + productAnzahl);
				return;
			}
		}*/
		invtList.add(invt);
	}


	public Kunde findCustomerByName(String name) {
		for (Kunde customer : kundenList) {
			if (customer.getName().equals(name)) {
				return customer;
			}
		}
		return null; 
	}

	public Inventory findProductById(int productId) {
		for (Inventory inventory : invtList) {
			if (inventory.getId() == productId) {
				return inventory;
			}
		}
		return null; 
	}

	public Inventory findProductByName(String name) {
		for (Inventory inventory : invtList) {
			if (inventory.getProduct().equals(name)) {
				return inventory;
			}
		}
		return null; 
	}
	
	private Order findOrderByName(String Name) {
	    for (Order order : this.orderList) {
	        if (order.getI().getProduct().equals(Name)) {
	            return order;
	        }
	    }
	    return null; 
	}
	
/*
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

	public void showReturnProduct(String Ptoduct) {
		v.showRueckgabe(Ptoduct);
	}
	public String UpdateKunde() {
		String updatedKundenList = v.showCustomer(this.kundenList);
		return updatedKundenList;
	}

	public String updateInventory() {
		String updatedInventoryList = v.showInventory(this.invtList);
		return updatedInventoryList;
	}

	public String updateOrder() {
		String upDatedOrderList = v.showOrder(this.orderList);
		return upDatedOrderList;
	}

	public int returnProductQuantity(Inventory p) {
		int temp = 0;
		for(Inventory temp1  : this.invtList) {
			if(temp1.getId() == p.getId()) {
				temp = temp1.getQuantity();
			}
		}
		return temp;
	}
	public List<Kunde> getKundenList() {
		return kundenList;
	}


	public void setKundenList(List<Kunde> kundenList) {
		this.kundenList = kundenList;
	}


	public List<Inventory> getInvtList() {
		return invtList;
	}


	public void setInvtList(List<Inventory> invtList) {
		this.invtList = invtList;
	}


	public List<Order> getOrderList() {
		return orderList;
	}


	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
}
