package view;

import javax.swing.JFrame;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import control.UsersControl;
import data.Data;
import entity.User;
import utils.PropertiesWrapper;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The first screen the user sees after opening the app. It lets the user enter
 * his credentials: username and password.
 * 
 * @author ondryk
 * @author thecodecook
 *
 */
public class Login extends JFrame {

	private static final long serialVersionUID = -5468999238024014958L;
	private static final String LOCALIZATION = "locale.app";

	private JTextField usernameField;
	private JPasswordField pwdPassword;
	private JLabel lblLogin;
	private JButton btnLogin;

	private ResourceBundle rb;

	public Login() {
		rb = ResourceBundle.getBundle(LOCALIZATION,
				Locale.forLanguageTag(PropertiesWrapper.getProperties().getProperty("locale")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 200);

		setTitle(rb.getString("login.title"));

		getContentPane().setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		lblLogin = new JLabel(rb.getString("login.title"));
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(lblLogin, "6, 2");

		JLabel lblUsername = new JLabel(rb.getString("login.username"));
		getContentPane().add(lblUsername, "4, 6, right, default");

		usernameField = new JTextField();
		usernameField.setText(PropertiesWrapper.getProperties().getProperty("login.default.username"));
		getContentPane().add(usernameField, "6, 6, left, default");
		usernameField.setColumns(16);

		JLabel lblPassword = new JLabel(rb.getString("login.password"));
		getContentPane().add(lblPassword, "4, 8, right, default");

		pwdPassword = new JPasswordField();
		pwdPassword.setColumns(16);
		pwdPassword.setText(PropertiesWrapper.getProperties().getProperty("login.default.password"));
		getContentPane().add(pwdPassword, "6, 8, left, default");

		btnLogin = new JButton(rb.getString("login.action"));
		getContentPane().add(btnLogin, "6, 12");

		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				signIn();
			}
		});

		setResizable(false);
	}

	private void signIn() {
		User user = UsersControl.getUser(usernameField.getText(), String.valueOf(pwdPassword.getPassword()));

		if (user == null) {
			JOptionPane.showMessageDialog(this, rb.getString("login.error.nexist"), rb.getString("app.error.title"),
					JOptionPane.ERROR_MESSAGE);
		} else {
			Data.user = user;
			JFrame homeWindow = new view.Home();
			homeWindow.setVisible(true);
			this.setVisible(false);
			this.dispose();
		}

	}

}
