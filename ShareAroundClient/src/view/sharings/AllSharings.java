package view.sharings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import control.SharingsControl;
import data.Data;
import entity.Sharing;

/**
 * The window showing all sharings sorted by their creation date.
 * 
 * @author ondryk
 * @author thecodecook
 *
 */
public class AllSharings extends Sharings {

	private static final long serialVersionUID = -8924973399770613340L;

	/**
	 * Create the frame.
	 */
	public AllSharings() {

		super();

		columnNames = new String[] { rb.getString("sharing.type"), rb.getString("sharing.label"),
				rb.getString("sharing.price"), rb.getString("sharing.author") };
		populateTable();
		setTitle(MessageFormat.format(rb.getString("allsharings.title"), Data.group.getName()));

		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index < 0) {
					JOptionPane.showMessageDialog(contentPane, rb.getString("app.warning.notSelected"),
							rb.getString("app.warning.title"), JOptionPane.WARNING_MESSAGE);
				} else {

					JFrame detailWindow = new SharingDetail(Data.sharings.get(index));
					detailWindow.setVisible(true);
				}
			}
		});

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				updateUI();
			}
		});

		updateUI();

	}

	protected void addRows() {
		for (Sharing o : Data.sharings) {
			Sharing sharing = (Sharing) o;

			String[] values = new String[columnNames.length];
			values[0] = sharing.getType().getName();
			values[1] = sharing.getLabel();
			values[2] = sharing.getPrice();
			values[3] = sharing.getUser().getUserName();

			model.addRow(values);
		}
	}

	protected void filter() {
		for (int i = model.getRowCount() - 1; i > -1; i--) {
			model.removeRow(i);
		}

		int selectedIndex = comboBox.getSelectedIndex();
		if (selectedIndex == 0) {
			SharingsControl.getAllSharingsOfGroup();
		} else {
			SharingsControl.getAllSharingsOfGroup(Data.sharingTypes.get(selectedIndex - 1));
		}

		addRows();
		updateUI();
	}

	protected void updateUI() {
		btnDetail.setEnabled(table.getSelectedRow() >= 0);
	}

}
