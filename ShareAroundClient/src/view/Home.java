package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import control.ReactionsControl;
import control.SharingsControl;
import data.Data;
import utils.PropertiesWrapper;
import view.sharings.Sharings;
import view.reactions.MyReactions;
import view.sharings.AllSharings;
import view.sharings.MySharings;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;

/**
 * The home view of the applicaiton. It's the view the users sees right after
 * signing in.
 * 
 * @author ondryk
 * @author thecodecook
 *
 */
public class Home extends JFrame {

	private static final long serialVersionUID = -302936747490402708L;
	private static final String LOCALIZATION = "locale.app";

	private JLabel lblGroup;
	private JComboBox<String> groupComboBox;
	private JButton btnNewSharing;
	private JButton btnMySharings;
	private JButton btnMyReactions;
	private JPanel panel;
	private JLabel lblUsername;
	private JLabel lblName;
	private JLabel lblEmail;
	private JLabel lblTelephone;

	/**
	 * Create the frame.
	 */
	public Home() {
		ResourceBundle rb = ResourceBundle.getBundle(LOCALIZATION,
				Locale.forLanguageTag(PropertiesWrapper.getProperties().getProperty("locale")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 220);
		setTitle(rb.getString("home.title"));

		getContentPane().setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), }));

		lblGroup = new JLabel(rb.getString("home.groupLabel"));
		getContentPane().add(lblGroup, "2, 2, right, default");

		groupComboBox = new JComboBox<String>(getGroupNamesArray());
		groupComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Data.group = Data.user.getGroups().get(groupComboBox.getSelectedIndex());
			}
		});
		groupComboBox.setBounds(278, 60, 115, 23);
		getContentPane().add(groupComboBox, "4, 2, 6, 1, fill, default");
		groupComboBox.setSelectedIndex(0);

		panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, rb.getString("user"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel, "12, 2, 13, 7, fill, fill");
		panel.setLayout(new GridLayout(5, 0, 0, 0));

		lblUsername = new JLabel(Data.user.getUserName());
		panel.add(lblUsername);

		lblName = new JLabel(Data.user.getFirstName() + " " + Data.user.getLastName());
		panel.add(lblName);

		lblEmail = new JLabel(Data.user.getEmail());
		panel.add(lblEmail);

		lblTelephone = new JLabel(Data.user.getPhone());
		panel.add(lblTelephone);

		btnNewSharing = new JButton(rb.getString("home.newSharings"));
		btnNewSharing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				SharingsControl.getAllSharingsOfGroup();

				Sharings sharingWindow = new AllSharings();
				sharingWindow.setVisible(true);
				close();

			}
		});
		getContentPane().add(btnNewSharing, "2, 4, 8, 1");

		btnMySharings = new JButton(rb.getString("home.mySharings"));
		btnMySharings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SharingsControl.getAllSharingsOfUser();

				Sharings sharingWindow = new MySharings();
				sharingWindow.setVisible(true);
				close();
			}
		});
		getContentPane().add(btnMySharings, "2, 6, 8, 1");

		btnMyReactions = new JButton(rb.getString("home.myReactions"));
		btnMyReactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ReactionsControl.getReactionsByUser();
				MyReactions myReactionsWindow = new MyReactions();
				myReactionsWindow.setVisible(true);
				close();
			}
		});
		getContentPane().add(btnMyReactions, "2, 8, 8, 1");
		
		/*
		 * Let's hide this for now.
		 */
		btnMyReactions.setVisible(false);
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
