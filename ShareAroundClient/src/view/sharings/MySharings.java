package view.sharings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import control.ReactionsControl;
import control.SharingsControl;
import data.Data;
import entity.Sharing;
import view.reactions.Reactions;

import javax.swing.JButton;

public class MySharings extends Sharings {

	private static final long serialVersionUID = -5341799549681717993L;

	private JButton btnShowreactions;
	private JButton btnAddnew;

	public MySharings() {

		super();

		setTitle(rb.getString("mysharings.title"));

		btnShowreactions = new JButton(rb.getString("mysharings.showReactions"));
		sidePanel.add(btnShowreactions, "1, 6, fill, top");

		btnAddnew = new JButton(rb.getString("mysharings.addNew"));
		sidePanel.add(btnAddnew, "1, 8");

		btnAddnew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSharing addSharing = new AddSharing();
				addSharing.setVisible(true);
			}
		});

		btnShowreactions.addActionListener(new ActionListener() {
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
					JFrame mySharingssWindow = new MySharingDetail(Data.sharings.get(index), MySharings.this);
					mySharingssWindow.setVisible(true);

				}
			}
		});
				
		columnNames = new String[] { rb.getString("sharing.type"), rb.getString("sharing.label"),
				rb.getString("sharing.price"), rb.getString("sharing.state"), rb.getString("sharing.group"),
				rb.getString("sharing.reactions") };
		populateTable();
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            updateUI();
	        }
	    });
		
		updateUI();
	}
	
	public void refreshData() {
		filter();
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
		for (int i = model.getRowCount() - 1; i > -1; i--) {
			model.removeRow(i);
		}

		int selectedIndex = comboBox.getSelectedIndex();
		if (selectedIndex == 0) {
			SharingsControl.getAllSharingsOfUser();
		} else {
			SharingsControl.getAllSharingsOfUser(Data.sharingTypes.get(selectedIndex - 1));
		}

		addRows();
		updateUI();
	}

	protected void updateUI() {
		btnShowreactions.setEnabled(table.getSelectedRow() >= 0);
		btnDetail.setEnabled(table.getSelectedRow() >= 0);
	}

}
