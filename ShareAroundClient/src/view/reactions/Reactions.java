package view.reactions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import data.Data;
import entity.Reaction;
import view.TableWindow;


/**
 * 
 * @author ondryk
 *
 */
public class Reactions extends TableWindow {

	
	
	protected JButton btnDetail;
	
		
	/**
	 * Create the frame.
	 */
	public Reactions() {

		super();
		
		columnNames = new String[]{"Užívate¾", "Popis", "Dátum"};
		setTitle("Reakcie na moje zdie¾anie");
		
		populateTable();
		
		btnDetail = new JButton("Detail");	
			
		btnDetail.setBounds(412, 220, 89, 23);
		contentPane.add(btnDetail);
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index < 0) 
				{
					JOptionPane.showMessageDialog(contentPane, "Vyberte riadok");
				}
				else
				{
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

}
