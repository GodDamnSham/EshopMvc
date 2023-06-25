package main;

import java.util.Scanner;

import Controller.Controller;
import Model.Inventory;
import Model.Kunde;
import Model.Order;
import View.view;
import helper.AusgabeKonsole;
import helper.Eingabe;
import helper.IAusgabe;
/* create a Kunde , create an Inventory , Kunden kann Bestellung anlegen 
 * kunden kann Bestellung löschen, kunden kann seine Adresse ändern, 
 * inventory sachen löschen , ruckversand und artikel erhöhen*/
public class verwaltung {
	private final IAusgabe ausgabe;
	private Controller controller;
	//private view view;

	public verwaltung(IAusgabe ausgabe) {
		this.ausgabe = ausgabe;
		this.controller = new Controller(new view());
	}

	public void start() {
		ausgabe.Print("Verwaltung von E-Shop");

		int eingabe = -1;

		while (eingabe != 0) {
			ausgabe.Print("\n\n"
					+ "0 - Programm beenden\n"
					+ "1 - Kunde hinzufuegen\n"
					+ "2 - Produkt hinzufuegen\n"
					+ "3 - Bestellung hinzufuegen\n"
					+ "4 - show Kunden\n"
					+ "5 - show inventory\n"
					+ "6 - show Order\n"
					+ "7 - Delete order\n");

			eingabe = Eingabe.leseInt();
			switch (eingabe) {
			case 1:
				kundenHinzufuegen();
				break;
			case 2:
				inventoryHinzufuegen();
				break;
			case 3:
				orderHinzufuegen();
				break;
			case 4:
				showKunde();
				break;
			case 5:
				showInventory();
				break;
			case 6:
				showOrder();
				break;
			case 7:
			    orderlöschen();
			}
		}
	}



	

	private void kundenHinzufuegen() {
		Scanner myObj = new Scanner(System.in);
		System.out.println("Name des Kunden:");
		String name = myObj.nextLine();
		System.out.println("Adresse des Kunden:");
		String addr = myObj.nextLine();
		Kunde customer = new Kunde(name, addr);
		controller.kundeHinzufuegen(customer); 
	}

	private void showKunde() {
		System.out.println(controller.UpdateKunde());

	}

	private void inventoryHinzufuegen() {
		Scanner myObj = new Scanner(System.in);
		System.out.println("Produktname:");
		String product = myObj.nextLine();
		System.out.println("Menge von dem Produkt:");
		int productAnzahl = myObj.nextInt();
		Inventory inventory = new Inventory(product , productAnzahl);
		controller.invtHinzufuegen(inventory);
	}

	private void showInventory() {
		System.out.println(controller.updateInventory());

	}

	private void orderHinzufuegen() {
		Scanner myObj = new Scanner(System.in);
		System.out.println("Kundenname:");
		String customerName = myObj.nextLine();

		Kunde customer = controller.findCustomerByName(customerName);
		if (customer != null) {
			System.out.println(controller.updateInventory());

			System.out.println("Produkt-ID:");
			int productId = Eingabe.leseInt();
			System.out.println("Anahl von Produkten:");
			int productAnzahl = Eingabe.leseInt();
			Inventory product = controller.findProductById(productId);
			if (product != null) {
				controller.orderHinzufuegen(new Order(customer, product) , productAnzahl);
			} else {
				System.out.println("Produkt nicht gefunden!");
			}
		} else {
			System.out.println("Kunde nicht gefunden!");
		}
	}
	private void showOrder() {
		System.out.println(controller.updateOrder());
	}
	
	private void orderlöschen() {
		// TODO Auto-generated method stub
		System.out.println(controller.updateOrder());
		System.out.println(controller.updateInventory());

		System.out.println("Product name you want to delete:");
		String productName = Eingabe.leseString();
		//Inventory product = controller.findProductById(productId);
		if (productName != null) {
			controller.orderLoeschen(productName);
		} else {
			System.out.println("Produkt nicht gefunden!");
		}
		
	}
	public static void main(String[] args) {
		verwaltung verwaltung = new verwaltung(new AusgabeKonsole());
		verwaltung.start();
	}
}
