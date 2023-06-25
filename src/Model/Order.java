package Model;

import java.util.ArrayList;
import java.util.List;

public class Order {

	
	private Kunde k;
	private Inventory i;
	
	public Order() {
	}
	public Order(Kunde k , Inventory i) {
		this.k = k;
		this.i = i;
	}
	
	public Kunde getK() {
		return k;
	}

	public void setK(Kunde k) {
		this.k = k;
	}

	public Inventory getI() {
		return i;
	}

	public void setI(Inventory i) {
		this.i = i;
	}
	
	public void setAnzahl(int anzahl) {
		this.getI().setQuantity(anzahl);
	}
	public int getAnzahl() {
		return this.getI().getQuantity();
	}
	
	@Override
	public String toString() {
	    String tableFormat = "%-10s %-30s %-10s %-10s\n";
	    StringBuilder sb = new StringBuilder();

	    // Table header
	    //sb.append(String.format(tableFormat, "kunde", "adresse", "gekauft","Anzahl"));
	    //sb.append(String.format(tableFormat, "----------", "------------------------------", "----------","----------"));

	    // Table row
	    sb.append(String.format(tableFormat, k.getName(), k.getAdress(), i.getProduct() , i.getQuantity()));

	    return sb.toString();
	}

	
}
