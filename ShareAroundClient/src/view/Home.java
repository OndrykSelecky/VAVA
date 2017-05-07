package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.ReactionsControl;
import control.SharingsControl;
import data.Data;
import entity.Group;
import view.sharings.Sharings;
import view.reactions.MyReactions;
import view.sharings.AllSharings;
import view.sharings.MySharings;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class Home extends JFrame {

	private JPanel contentPane;	
    private JComboBox comboBox;
    
    
	/**
	 * Create the frame.
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("Domov");
		
		JLabel lblSkupina = new JLabel("Skupina: ");
		lblSkupina.setBounds(213, 26, 180, 14);
		contentPane.add(lblSkupina);
		
		
		comboBox = new JComboBox(getGroupNamesArray());
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Data.group = Data.user.getGroups().get(comboBox.getSelectedIndex());
				lblSkupina.setText("Skupina: "+ Data.group.getName());
				
			}
		});
		comboBox.setBounds(278, 60, 115, 23);
		contentPane.add(comboBox);
		comboBox.setSelectedIndex(0);
		
		
		JButton btnZobraziZdieania = new JButton("Nov\u00E9 zdie\u013Eania");
		btnZobraziZdieania.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SharingsControl.getAllSharingsOfGroup();
				
				Sharings sharingWindow = new AllSharings();
				sharingWindow.setVisible(true);
				close();
				
			}
		});
		btnZobraziZdieania.setBounds(43, 60, 148, 23);
		contentPane.add(btnZobraziZdieania);
		
		JButton btnMojeZdieania = new JButton("Moje zdie\u013Eania");
		btnMojeZdieania.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SharingsControl.getAllSharingsOfUser();
				
				Sharings sharingWindow = new MySharings();
				sharingWindow.setVisible(true);
				close();
			}
		});
		btnMojeZdieania.setBounds(43, 145, 148, 23);
		contentPane.add(btnMojeZdieania);
		
		JButton btnMojeReakcie = new JButton("Moje reakcie");
		btnMojeReakcie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ReactionsControl.getReactionsByUser();
				MyReactions myReactionsWindow = new MyReactions();
				myReactionsWindow.setVisible(true);
				close();
			}
		});
		btnMojeReakcie.setBounds(43, 179, 148, 23);
		contentPane.add(btnMojeReakcie);
		
		JButton btnSp = new JButton("Sp\u00E4\u0165");
		btnSp.setBounds(278, 179, 118, 23);
		contentPane.add(btnSp);
		
	}
	
	private String[] getGroupNamesArray()
	{
				
		String[] groupNamesArray = new String[Data.user.getGroups().size()];
		for (int i = 0; i< Data.user.getGroups().size(); i++)
		{
			
			groupNamesArray[i] = Data.user.getGroups().get(i).getName();
		}
		
		
		return groupNamesArray;	
				
	}

	
	protected void close()
	{
		this.dispose();
	}
		
}
