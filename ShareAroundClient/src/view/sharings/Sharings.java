package view.sharings;

import data.Data;
import view.Home;
import view.TableWindow;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public abstract class Sharings extends TableWindow {

		
	protected JComboBox<?> comboBox;
	protected JLabel lblTyp;
	protected JButton btnDetail;
	private JButton btnFiltrova;
	
		
	/**
	 * Create the frame.
	 */
	public Sharings() {
		
		super();
		
		lblTyp = new JLabel("Typ:");
		lblTyp.setBounds(26, 224, 36, 14);
		contentPane.add(lblTyp);
		
		String[] sharingTypes = new String[Data.sharingTypes.size()];
		
		for (int i=0; i< Data.sharingTypes.size(); i++)
		{
			sharingTypes[i] = Data.sharingTypes.get(i).getName();			
		}
		
		
		comboBox = new JComboBox<Object>(sharingTypes);
		comboBox.setBounds(25, 251, 135, 20);
		contentPane.add(comboBox);
		
		
		
		btnDetail = new JButton("Detail");	
			
		btnDetail.setBounds(412, 220, 89, 23);
		contentPane.add(btnDetail);
		
		btnFiltrova = new JButton("Filtrova\u0165");
		btnFiltrova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filter();
			}
		});
		btnFiltrova.setBounds(72, 221, 89, 23);
		getContentPane().add(btnFiltrova);
		
				
	}
	
		
	protected abstract void addRows();	

	protected abstract void filter();
	
	protected void close()
	{
		Home home = new Home();
		home.setVisible(true);
		this.dispose();
	}

}
