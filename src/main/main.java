package main;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import UserInterface.GUIVerwaltung;
import helper.AusgabeKonsole;

public class main {

	public static void main(String[] args) {
		try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUIVerwaltung(new AusgabeKonsole());
            }
        });
    }

}
