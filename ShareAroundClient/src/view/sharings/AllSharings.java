package view.sharings;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.SharingsControl;
import data.Data;
import entity.Sharing;
import entity.SharingType;


/**
 * Okno, ktoré zobrazuje aktuálne zdie¾ania všetkých používate¾ov zoradené pod¾a dátumu
 * @author ondryk
 *
 */
public class AllSharings extends Sharings {

	
	private static final long serialVersionUID = -8924973399770613340L;

	/**
	 * Create the frame.
	 */
	public AllSharings() {

		super();
		
		columnNames = new String[]{"Typ", "Predmet", "Cena", "Autor"};
		populateTable();
		setTitle("Nové zdie¾ania užívate¾ov");
		
		
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index < 0) 
				{
					JOptionPane.showMessageDialog(contentPane, "Row is not selected");
				}
				else
				{
									
					JFrame detailWindow = new SharingDetail(Data.sharings.get(index));
					detailWindow.setVisible(true);
				}
			}
		});
		
	}

	protected void addRows()
	{
		for (Sharing o : Data.sharings)
		{
			Sharing sharing = (Sharing) o;
						
			String[] values = new String[columnNames.length];
			values[0] = sharing.getType().getName();
			values[1] = sharing.getLabel();
			values[2] = sharing.getPrice();
			values[3] = sharing.getUser().getUserName();
			
			model.addRow(values);
		}
	}
	
	protected void filter()
	{
		SharingsControl.getAllSharingsOfGroup(Data.sharingTypes.get(comboBox.getSelectedIndex()));
		AllSharings allSharingsWindow = new AllSharings();
		allSharingsWindow.setVisible(true);
		this.dispose();
	}
	
}
