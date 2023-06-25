package UserInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginGUI {
	private JPanel panel;
	private  JPasswordField passwordField;
	private  JTextField username;

	public LoginGUI(ActionListener loginButtonListener) {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JLabel welcomeLabel = new JLabel("Welcome to our E-Shop! Please log in:");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
		panel.add(welcomeLabel, BorderLayout.NORTH);

		JPanel formPanel = new JPanel();
		formPanel.setLayout(new FlowLayout());

		JLabel passwordLabel = new JLabel("Password:");
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(150, 30));
		
		JLabel UsernameLabel = new JLabel("Username:");
		username = new JTextField();
		username.setPreferredSize(new Dimension(150, 30));
		
		JButton loginButton = new JButton("Log In");
		loginButton.addActionListener(loginButtonListener);

		//panel.add(username);
		formPanel.add(UsernameLabel);
		formPanel.add(username);
		formPanel.add(passwordLabel);
		formPanel.add(passwordField);
		formPanel.add(loginButton);
		
		panel.add(formPanel, BorderLayout.CENTER);
		username.requestFocus();
	}

	public JPanel getPanel() {
		return panel;
	}

	public String getPassword() {
		return new String(passwordField.getPassword());
	}
	
	public String getUsername() {
		return username.getText();
	}
	public void resetFields() {
        username.setText("");
        passwordField.setText("");
    }

}
