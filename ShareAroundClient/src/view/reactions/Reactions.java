package view.reactions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import data.Data;
import entity.Reaction;
import utils.PropertiesWrapper;
import view.TableWindow;



/**
 * Reactions to my sharing screen.
 * 
 * @author ondryk
 * @author thecodecook
 */
public class Reactions extends TableWindow {

	private static final long serialVersionUID = -3018811350615198890L;
	private static final String LOCALIZATION = "locale.app";

	protected JButton btnDetail;
		
	/**
	 * Create the frame.
	 */
	public Reactions() {

		super();
		
		ResourceBundle rb = ResourceBundle.getBundle(LOCALIZATION,
				Locale.forLanguageTag(PropertiesWrapper.getProperties().getProperty("locale")));

		columnNames = new String[]{rb.getString("user"), rb.getString("sharing.desc"), rb.getString("date")};
		setTitle(rb.getString("sharingreaction.title"));
		
		populateTable();
		
		btnDetail = new JButton("Detail");
		sidePanel.add(btnDetail, "1, 4");
			
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index < 0) {
					JOptionPane.showMessageDialog(contentPane, rb.getString("app.warning.notSelected"),
							rb.getString("app.warning.title"), JOptionPane.WARNING_MESSAGE);
				} else {
					ReactionDetail reactionDetailWindow = new ReactionDetail(Data.reactions.get(index));
					reactionDetailWindow.setVisible(true);
				}
			}
		});
				
	}
	
	protected void addRows()
	{
		for (Reaction o : Data.reactions)
		{
			Reaction reaction = (Reaction) o;
						
			String[] values = new String[columnNames.length];
			values[0] = reaction.getUser().getUserName();
			values[1] = reaction.getMessage();
			values[2] = reaction.getDate().toString();			
			
			model.addRow(values);
		}
	}
	
	protected void updateUI() {
		btnDetail.setEnabled(table.getSelectedRow() >= 0);
	}

}
