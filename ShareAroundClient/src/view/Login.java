package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.UsersControl;
import data.Data;
import entity.User;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5468999238024014958L;
	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	
	
	
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Prihlásenie");
		
		JLabel lblMeno = new JLabel("Prihlasovacie meno");
		lblMeno.setBounds(49, 53, 107, 14);
		contentPane.add(lblMeno);
		
		JLabel lblNewLabel = new JLabel("Heslo");
		lblNewLabel.setBounds(49, 89, 46, 14);
		contentPane.add(lblNewLabel);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(166, 50, 127, 20);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		textFieldUsername.setText("Peso");
		
		passwordField = new JPasswordField();
		passwordField.setBounds(166, 86, 127, 20);
		contentPane.add(passwordField);
		passwordField.setText("123456");
		
		JButton btnPrihlsi = new JButton("Prihl\u00E1si\u0165");		
		btnPrihlsi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				signIn();
				
			}
		});
		btnPrihlsi.setBounds(143, 155, 89, 23);
		contentPane.add(btnPrihlsi);
	}
	
	
	private void signIn()
	{
		User user = UsersControl.getUser(textFieldUsername.getText(), String.valueOf(passwordField.getPassword()));

		if (user == null) 
		{
			JOptionPane.showMessageDialog(this, "User doesn't exist");
		}
		else
		{
			Data.user = user;
			JFrame homeWindow = new view.Home();
			homeWindow.setVisible(true);
			this.setVisible(false);
			this.dispose();
		}
		
		
	}
	
}
