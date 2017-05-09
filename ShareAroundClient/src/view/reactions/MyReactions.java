package view.reactions;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Data;
import entity.Reaction;
import utils.PropertiesWrapper;
import view.Home;
import view.TableWindow;
import view.sharings.SharingDetail;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class MyReactions extends TableWindow {
	
	private JPanel contentPane;

	
	/**
	 * Create the frame.
	 */
	public MyReactions() {
		super();
		
		setTitle(rb.getString("myreactions.title"));
		
		JButton btnDetailyReakcie = new JButton("Detaily reakcie");
		btnDetailyReakcie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index < 0) 
				{
					JOptionPane.showMessageDialog(contentPane, "Vyberte riadok");
				}
				else
				{
					MyReactionSharingDetail sharingDetailWindow = new MyReactionSharingDetail(Data.reactions.get(index));
					sharingDetailWindow.setVisible(true);
				}
				
				
			}
		});
		btnDetailyReakcie.setBounds(334, 227, 171, 23);
		getContentPane().add(btnDetailyReakcie);
		
		JButton btnSp = new JButton("Sp\u00E4\u0165");
		btnSp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home homeWindow = new Home();
				homeWindow.setVisible(true);
				close();
			}
		});
		btnSp.setBounds(111, 227, 89, 23);
		getContentPane().add(btnSp);
		
		columnNames = new String[]{"Autor zdie�ania", "Typ zdie�ania", "Predmet zdie�ania", "D�tum reakcie", "Platnos�"};
		
		populateTable();
		
	}
	
	protected void addRows()
	{
		for (Reaction o : Data.reactions)
		{
			Reaction reaction = (Reaction) o;
						
			String[] values = new String[columnNames.length];
			values[0] = reaction.getSharing().getUser().getUserName();
			values[1] = reaction.getSharing().getType().getName();
			values[2] = reaction.getSharing().getLabel();
			values[3] = reaction.getDate().toString();
			if (reaction.getSharing().isActive() == true)
			{
				values[4] = "Platn�";
			}
			else
			{
				values[4] = "Ukon�en�";
			}
			
			model.addRow(values);
		}
	}
	
	

}
