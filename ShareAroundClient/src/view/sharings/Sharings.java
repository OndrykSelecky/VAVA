package view.sharings;

import data.Data;
import view.Home;
import view.TableWindow;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The base view for all sharings.
 * 
 * @author ondryk
 * @author thecodecook
 *
 */
public abstract class Sharings extends TableWindow {

	private static final long serialVersionUID = 3806274174674507001L;
	protected JComboBox<String> comboBox;
	protected JLabel lblTyp;
	protected JButton btnDetail;

	/**
	 * Create the frame.
	 */
	public Sharings() {

		super();

		JLabel lblTyp = new JLabel(rb.getString("sharing.type"));
		panel.add(lblTyp, "2, 2");

		String[] sharingTypes = new String[Data.sharingTypes.size() + 1];

		sharingTypes[0] = rb.getString("app.filter.all");
		for (int i = 0; i < Data.sharingTypes.size(); i++) {
			sharingTypes[i + 1] = Data.sharingTypes.get(i).getName();
		}
		comboBox = new JComboBox<String>(sharingTypes);
		panel.add(comboBox, "4, 2, 17, 1, left, default");

		JButton btnFilter = new JButton(rb.getString("app.filter"));
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filter();
			}
		});
		panel.add(btnFilter, "20, 4, right, default");

		btnDetail = new JButton("Detail");
		sidePanel.add(btnDetail, "1, 4");
	}

	protected abstract void addRows();

	protected abstract void filter();

	protected void close() {
		Home home = new Home();
		home.setVisible(true);
		this.dispose();
	}

}
