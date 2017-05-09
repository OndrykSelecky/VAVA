package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.UsersControl;
import data.Data;
import entity.User;
import utils.PropertiesWrapper;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.ResourceBundle;

public class Login extends JFrame {

	private static final long serialVersionUID = -5468999238024014958L;
	private static final String LOCALIZATION = "locale.app";

	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;

	private ResourceBundle rb;
	
	public Login() {
		rb = ResourceBundle.getBundle(LOCALIZATION, Locale.forLanguageTag(PropertiesWrapper.getProperties().getProperty("locale")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setTitle(rb.getString("login.title"));

		JLabel lblUsername = new JLabel(rb.getString("login.username"));
		lblUsername.setBounds(49, 53, 150, 14);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel(rb.getString("login.password"));
		lblPassword.setBounds(49, 89, 150, 14);
		contentPane.add(lblPassword);

		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(170, 50, 127, 20);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		textFieldUsername.setText(PropertiesWrapper.getProperties().getProperty("login.default.username"));

		passwordField = new JPasswordField();
		passwordField.setBounds(170, 86, 127, 20);
		contentPane.add(passwordField);
		passwordField.setText(PropertiesWrapper.getProperties().getProperty("login.default.password"));

		JButton btnLogin = new JButton(rb.getString("login.action"));
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				signIn();

			}
		});
		btnLogin.setBounds(130, 155, 89, 23);
		contentPane.add(btnLogin);
		setResizable(false);
	}

	private void signIn() {
		User user = UsersControl.getUser(textFieldUsername.getText(), String.valueOf(passwordField.getPassword()));

		if (user == null) {
			JOptionPane.showMessageDialog(this, rb.getString("login.error.nexist"), rb.getString("app.error.title"), JOptionPane.ERROR_MESSAGE);
		} else {
			Data.user = user;
			JFrame homeWindow = new view.Home();
			homeWindow.setVisible(true);
			this.setVisible(false);
			this.dispose();
		}

	}

}
