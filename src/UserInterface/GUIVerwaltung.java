package UserInterface;
import javax.swing.*;

import BuisnessLogic.Controller;
import Model.Inventory;
import Model.Kunde;
import Model.Order;
import View.view;
import helper.IAusgabe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIVerwaltung extends JFrame {
	private static final long serialVersionUID = 1L;
	private final IAusgabe ausgabe;
	private Controller controller;
	private JTextArea outputTextArea;
	private AccountCred accountCredentials;
    private AccountType currentAccountType;
    private LoginGUI loginPanel;

	public GUIVerwaltung(IAusgabe ausgabe) {
		this.ausgabe = ausgabe;
		this.controller = new Controller(new view());
		this.accountCredentials = new AccountCred();
		initializeUserAccounts();
		initializeGUI();
		controller.invtHinzufuegen(new Inventory("shampoo" , 200));
		controller.invtHinzufuegen(new Inventory("seife" , 150));
		controller.invtHinzufuegen(new Inventory("reis" , 20));
		controller.invtHinzufuegen(new Inventory("milch" , 100));
		controller.invtHinzufuegen(new Inventory("pizza" , 20));
		controller.kundeHinzufuegen(new Kunde("malik" , "Rheiberger str. 19 85057 Ingolstadt"));
	}
	
	//creating user and admin account
	private void initializeUserAccounts() {
        accountCredentials.setUsername(AccountType.ADMIN, "admin");
        accountCredentials.setPassword(AccountType.ADMIN, "admin123");

        accountCredentials.setUsername(AccountType.USER, "user");
        accountCredentials.setPassword(AccountType.USER, "user123");
    }
	
	//initializing the Interface..
	private void initializeGUI() {
		setTitle("Verwaltung von E-Shop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		loginPanel = new LoginGUI(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButtonClicked(loginPanel.getPassword() , loginPanel.getUsername());
                
            }
        });

        add(loginPanel.getPanel(), BorderLayout.CENTER);
		outputTextArea = new JTextArea();
		pack();
		setSize(1000, 600);
		setVisible(true);
	}
	
	//Login buttion action
	private void loginButtonClicked(String password , String name) {
        if (checkCredentials(AccountType.ADMIN, password , name )) {
            currentAccountType = AccountType.ADMIN;
            this.handleLoginSuccess();
        } else if (checkCredentials(AccountType.USER, password , name)) {
            currentAccountType = AccountType.USER;
            this.handleLoginSuccess();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password!", "Login Error", JOptionPane.ERROR_MESSAGE);
            loginPanel.resetFields();
        }
    }
	
	//method for the checking credentials 
	private boolean checkCredentials(AccountType accountType, String password , String name) {
        String storedPassword = accountCredentials.getPassword(accountType);
        String storedUsername = accountCredentials.getUsername(accountType);
        return storedPassword.equals(password) && storedUsername.equals(name);
    }
	
	// handle the login success with switch statement
	private void handleLoginSuccess() {
        getContentPane().removeAll();
        // Show different options based on the account type
        switch (currentAccountType) {
            case ADMIN:
                this.showAdminOptions();
                break;
            case USER:
                this.showUserOptions();
                break;
        }
        revalidate();
        repaint();
    }
	
	// showing option for Admin
	private void showAdminOptions() {
        setTitle("Admin - Verwaltung von E-Shop");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logoutButtonClicked();
            }
        });
        buttonPanel.add(logoutButton);
        
        JButton addButton = new JButton("Einfügen");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminaddOptionSelected();
			}
		});
		buttonPanel.add(addButton);

		JButton showButton = new JButton("Zeigen");
		showButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showOptionSelected();
			}
		});
		buttonPanel.add(showButton);

		add(buttonPanel, BorderLayout.CENTER);

        
        outputTextArea = new JTextArea();
		outputTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(outputTextArea);
		scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 400));
		add(scrollPane, BorderLayout.SOUTH);
    }
	
	//showing user Option
	private void showUserOptions() {
        setTitle("User - Verwaltung von E-Shop");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logoutButtonClicked();
            }
        });
        
        buttonPanel.add(logoutButton);
        JButton addButton = new JButton("Einfügen");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				kundeAddOptionSelected();
			}
		});
		buttonPanel.add(addButton);

		JButton deleteButton = new JButton("Löschen");
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteOptionSelected();
			}
		});
		buttonPanel.add(deleteButton);

		JButton showButton = new JButton("Zeigen");
		showButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showOptionSelected();
			}
		});
		buttonPanel.add(showButton);

		add(buttonPanel, BorderLayout.CENTER);

        
        outputTextArea = new JTextArea();
		outputTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(outputTextArea);
		scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 400));
		add(scrollPane, BorderLayout.SOUTH);
    }
	
	private void kundeAddOptionSelected() {
		String[] options = { "Empfänger hinzufügen", "Bestellung hinzufügen" };
		String selectedOption = (String) JOptionPane.showInputDialog(this, "Select an option:", "Add", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (selectedOption != null) {
			switch (selectedOption) {
			case "Empfänger hinzufügen":
				addCustomerButtonClicked();
				break;
			case "Bestellung hinzufügen":
				addOrderButtonClicked();
				break;
			}
		}
	}
	private void AdminaddOptionSelected() {
		String[] options = {"Produkt hinzufügen" };
		String selectedOption = (String) JOptionPane.showInputDialog(this, "Select an option:", "Add", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (selectedOption != null) {
			switch (selectedOption) {
			case "Produkt hinzufügen":
				addInventoryButtonClicked();
				break;
			
			}
		}
	}

	private void deleteOptionSelected() {
		String[] options = { "Delete Order" };
		String selectedOption = (String) JOptionPane.showInputDialog(this, "Select an option:", "Delete", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (selectedOption != null) {
			switch (selectedOption) {
			case "Delete Order":
				deleteOrderButtonClicked();
				break;
			}
		}
	}
	
	private void showOptionSelected() {
		String[] options = { "Empfänger anzeigen", "Inventory anzeigen", "Order anzeigen" };
		String selectedOption = (String) JOptionPane.showInputDialog(this, "Select an option:", "Show", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (selectedOption != null) {
			switch (selectedOption) {
			case "Empfänger anzeigen":
				showCustomersButtonClicked();
				break;
			case "Inventory anzeigen":
				showInventoryButtonClicked();
				break;
			case "Order anzeigen":
				showOrderButtonClicked();
				break;
			}
		}
	}
// there is a user account and with this account a user can give his name or oder for someone else.
	private void addCustomerButtonClicked() {
		String name = JOptionPane.showInputDialog(this, "Empfänger eingeben:");
		String address = JOptionPane.showInputDialog(this, "Adresse des Kunden:");

		Kunde customer = new Kunde(name, address);
		Kunde k = controller.findCustomerByName(name);
		if(k==null) {
			controller.kundeHinzufuegen(customer);
			updateOutput("Empfänger hinzugefügt:\n" + customer.toString());
		}else {
			updateOutput("Kunde existiert!!\n");
		}
	}
// adding data in Inventory 
	private void addInventoryButtonClicked() {
		String name = JOptionPane.showInputDialog(this, "Produktname:");
		int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Anzahl:"));
		Inventory inventoryItem = new Inventory(name, quantity);
		Inventory product = controller.findProductByName(name);

		if(product == null) {
			controller.invtHinzufuegen(inventoryItem);
			updateOutput("Produkt hinzugefügt:\n " + inventoryItem.toString());
		}else {
			//controller.inventoryAnzahlErhöhen(product.getId(), quantity);
			controller.increaseProductQuantity(product, quantity);
			updateOutput("Product already in the Inventory\n ");
			updateOutput("increased quantity\n " + quantity );

		}
	}
//deleting data from inventory 
	private void deleteOrderButtonClicked() {
		this.showOrderButtonClicked();
		String customerName = JOptionPane.showInputDialog(this, "Costumer name:");
		//this.showInventoryButtonClicked();
		this.showOrderButtonClicked();
		String productName = JOptionPane.showInputDialog(this, "delete product name:");

		if (productName != null) {
			int reply = JOptionPane.showConfirmDialog(null, "you want to delete entire order?", "Order Delete", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
			    //JOptionPane.showMessageDialog(null, "HELLO");
			    controller.orderLoeschen(productName , customerName);   
			} else {
				int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Anzahl:"));
				controller.orderLoeschenMitAnzahl(productName, quantity , customerName);
			}	

		} else {

			updateOutput("Produkt nicht gefunden");
		}
	}
	

	private void addOrderButtonClicked() {
		this.showCustomersButtonClicked();
		String customerName = JOptionPane.showInputDialog(this, "Empfänger Name:");
		Kunde customer = controller.findCustomerByName(customerName);
		if (customer != null) {
			showInventoryButtonClicked();
			int productId = Integer.parseInt(JOptionPane.showInputDialog(this, "Product ID:"));
			int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Anzahl:"));
			Inventory product = controller.findProductById(productId);
			int tempQ = controller.returnProductQuantity(product);
			if (product != null && tempQ >= quantity) {
				controller.orderHinzufuegen(new Order(customer, product.clone()), quantity);
				updateOutput("Produkt in Bestellung hinzugefügt:\n" + "Product Name: " + product.getProduct() + ", Anzahl: " 
				+ quantity + ", für Kunde: " + customer.getName());
			} else {
				updateOutput("Produkt nicht gefunden oder die Menge von der gegebene Bestellung passt nicht!");
			}
		} else {
			updateOutput("Kunde nicht gefunden!");
		}
	}

	private void showCustomersButtonClicked() {
		updateOutput(controller.UpdateKunde());
	}

	private void showInventoryButtonClicked() {
		updateOutput(controller.updateInventory());
	}

	private void showOrderButtonClicked() {
		updateOutput(controller.updateOrder());
	}

	private void updateOutput(String text) {
		ausgabe.Print(text);
		outputTextArea.setText("");
	    Font consoleFont = new Font("Monospaced", Font.PLAIN, 12);
	    outputTextArea.setFont(consoleFont);
	    outputTextArea.append(text + "\n");

	}
	// action base of Logout button
	private void logoutButtonClicked() {
        currentAccountType = null;
        getContentPane().removeAll();
        getContentPane().add(loginPanel.getPanel(), BorderLayout.CENTER);
        setTitle("Verwaltung von E-Shop");
        loginPanel.resetFields();
        revalidate();
        repaint();
    }

}
