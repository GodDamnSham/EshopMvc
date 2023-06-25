package main;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import UserInterface.GUIVerwaltung;
import helper.AusgabeKonsole;

public class main {

	public static void main(String[] args) {
		//int adminOption = JOptionPane.showOptionDialog(null, "Are you an admin?", "Admin Verification", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		//boolean isAdmin = adminOption == JOptionPane.YES_OPTION;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUIVerwaltung(new AusgabeKonsole());
            }
        });
    }

}
