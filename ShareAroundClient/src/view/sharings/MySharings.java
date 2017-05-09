package view.sharings;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.ReactionsControl;
import control.SharingsControl;
import data.Data;
import entity.Reaction;
import entity.Sharing;
import session.ManageReactionsRemote;
import session.ManageSharingsRemote;
import view.reactions.Reactions;

import javax.naming.NamingException;
import javax.swing.JButton;

public class MySharings extends Sharings {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5341799549681717993L;

	public MySharings() {

		super();

		setTitle(rb.getString("mysharings.title"));

		JButton btnZobraziReakcie = new JButton(rb.getString("mysharings.title"));
		btnZobraziReakcie.setBounds(256, 220, 144, 23);
		getContentPane().add(btnZobraziReakcie);

		JButton btnPridaNov = new JButton(rb.getString("mysharings.addNew"));
		btnPridaNov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSharing addSharing = new AddSharing();
				addSharing.setVisible(true);
			}
		});
		btnPridaNov.setBounds(256, 243, 144, 23);
		getContentPane().add(btnPridaNov);
		btnZobraziReakcie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index < 0) {
					JOptionPane.showMessageDialog(contentPane, rb.getString("app.warning.notSelected"),
							rb.getString("app.warning.title"), JOptionPane.WARNING_MESSAGE);
				} else {
					ReactionsControl.getReactionsBySharing(Data.sharings.get(index));
					JFrame reactionsWindow = new Reactions();
					reactionsWindow.setVisible(true);

				}
			}
		});

		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index < 0) {
					JOptionPane.showMessageDialog(contentPane, rb.getString("app.warning.notSelected"),
							rb.getString("app.warning.title"), JOptionPane.WARNING_MESSAGE);
				} else {
					JFrame mySharingssWindow = new MySharingDetail(Data.sharings.get(index));
					mySharingssWindow.setVisible(true);

				}
			}
		});

		columnNames = new String[] { rb.getString("sharing.type"), rb.getString("sharing.label"),
				rb.getString("sharing.price"), rb.getString("sharing.state"), rb.getString("sharing.group"),
				rb.getString("sharing.reactions") };
		populateTable();
	}

	protected void addRows() {
		for (Sharing o : Data.sharings) {
			Sharing sharing = (Sharing) o;

			String[] values = new String[columnNames.length];
			values[0] = sharing.getType().getName();
			values[1] = sharing.getLabel();
			values[2] = sharing.getPrice();
			if (sharing.isActive() == true) {
				values[3] = rb.getString("sharing.state.active");
			} else {
				values[3] = rb.getString("sharing.state.inactive");
			}
			values[4] = sharing.getGroup().getName();
			values[5] = Integer.toString(sharing.getReactions().size());

			model.addRow(values);
		}
	}

	protected void filter() {
		SharingsControl.getAllSharingsOfUser(Data.sharingTypes.get(comboBox.getSelectedIndex()));
		MySharings mySharingsWindow = new MySharings();
		mySharingsWindow.setVisible(true);
		this.dispose();
	}

}
