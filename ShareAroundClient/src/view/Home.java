package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.ReactionsControl;
import control.SharingsControl;
import data.Data;
import entity.Group;
import utils.PropertiesWrapper;
import view.sharings.Sharings;
import view.reactions.MyReactions;
import view.sharings.AllSharings;
import view.sharings.MySharings;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class Home extends JFrame {

	private static final String LOCALIZATION = "locale.app";

	private JPanel contentPane;
	private JComboBox comboBox;

	/**
	 * Create the frame.
	 */
	public Home() {
		ResourceBundle rb = ResourceBundle.getBundle(LOCALIZATION,
				Locale.forLanguageTag(PropertiesWrapper.getProperties().getProperty("locale")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		this.setTitle(rb.getString("home.title"));

		JLabel lblGroup = new JLabel(rb.getString("home.groupLabel") + ": ");
		lblGroup.setBounds(213, 26, 180, 14);
		contentPane.add(lblGroup);

		comboBox = new JComboBox(getGroupNamesArray());
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Data.group = Data.user.getGroups().get(comboBox.getSelectedIndex());
				lblGroup.setText(rb.getString("home.groupLabel") + ": " + Data.group.getName());
			}
		});
		comboBox.setBounds(278, 60, 115, 23);
		contentPane.add(comboBox);
		comboBox.setSelectedIndex(0);

		JButton btnShowSharings = new JButton(rb.getString("home.newSharings"));
		btnShowSharings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				SharingsControl.getAllSharingsOfGroup();

				Sharings sharingWindow = new AllSharings();
				sharingWindow.setVisible(true);
				close();

			}
		});
		btnShowSharings.setBounds(43, 60, 148, 23);
		contentPane.add(btnShowSharings);

		JButton btnMySharings = new JButton(rb.getString("home.mySharings"));
		btnMySharings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SharingsControl.getAllSharingsOfUser();

				Sharings sharingWindow = new MySharings();
				sharingWindow.setVisible(true);
				close();
			}
		});
		btnMySharings.setBounds(43, 145, 148, 23);
		contentPane.add(btnMySharings);

		JButton btnMyReactions = new JButton(rb.getString("home.myReactions"));
		btnMyReactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ReactionsControl.getReactionsByUser();
				MyReactions myReactionsWindow = new MyReactions();
				myReactionsWindow.setVisible(true);
				close();
			}
		});
		btnMyReactions.setBounds(43, 179, 148, 23);
		contentPane.add(btnMyReactions);

		JButton btnBack = new JButton(rb.getString("app.back"));
		btnBack.setBounds(278, 179, 118, 23);
		contentPane.add(btnBack);
	}

	private String[] getGroupNamesArray() {

		String[] groupNamesArray = new String[Data.user.getGroups().size()];
		for (int i = 0; i < Data.user.getGroups().size(); i++) {

			groupNamesArray[i] = Data.user.getGroups().get(i).getName();
		}

		return groupNamesArray;

	}

	protected void close() {
		this.dispose();
	}

}
