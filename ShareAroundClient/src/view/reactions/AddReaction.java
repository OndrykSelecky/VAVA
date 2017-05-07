package view.reactions;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.ReactionsControl;
import data.Data;
import entity.Reaction;
import entity.Sharing;
import entity.User;
import session.ManageReactionsRemote;
import session.ManageUserRemote;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddReaction extends JFrame {

	private JPanel contentPane;
	private JTextPane textPane;
	private JButton btnOdoslaReakciu;
	private JRadioButton rdbtnAdresa;
	private JRadioButton rdbtnTelefnneslo;
	private JRadioButton rdbtnEmail;
	private JButton btnSp;
	private Sharing sharing;
	
	
	/**
	 * Create the frame.
	 */
	public AddReaction(Sharing sharing) {
		
		this.sharing=sharing;
		
		setTitle("Pridaù nov˙ reakciu");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSprva = new JLabel("Spr\u00E1va:");
		lblSprva.setBounds(21, 37, 46, 14);
		contentPane.add(lblSprva);
		
		textPane = new JTextPane();
		textPane.setBounds(87, 37, 292, 95);
		contentPane.add(textPane);
		
		btnOdoslaReakciu = new JButton("Odosla\u0165 reakciu");
		btnOdoslaReakciu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reaction reaction = new Reaction();
				reaction.setMessage(textPane.getText());
				reaction.setShowAddress(rdbtnAdresa.isSelected());
				reaction.setShowEmail(rdbtnEmail.isSelected());
				reaction.setShowPhone(rdbtnTelefnneslo.isSelected());
				reaction.setSharing(sharing);
				reaction.setUser(Data.user);
				
				ReactionsControl.addNewReaction(reaction);
				
				close();
			}
		});
		btnOdoslaReakciu.setBounds(237, 227, 128, 23);
		contentPane.add(btnOdoslaReakciu);
		
		JLabel lblOdosladaje = new JLabel("Odosla\u0165 \u00FAdaje:");
		lblOdosladaje.setBounds(21, 172, 85, 14);
		contentPane.add(lblOdosladaje);
		
		rdbtnAdresa = new JRadioButton("Adresa");
		rdbtnAdresa.setBounds(118, 168, 75, 23);
		contentPane.add(rdbtnAdresa);
		
		rdbtnTelefnneslo = new JRadioButton("Telef\u00F3nne \u010D\u00EDslo");
		rdbtnTelefnneslo.setBounds(195, 168, 128, 23);
		contentPane.add(rdbtnTelefnneslo);
		
		rdbtnEmail = new JRadioButton("Email");
		rdbtnEmail.setBounds(325, 168, 89, 23);
		contentPane.add(rdbtnEmail);
		
		btnSp = new JButton("Sp\u00E4\u0165");
		btnSp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnSp.setBounds(67, 227, 89, 23);
		contentPane.add(btnSp);
	}
	
	
	
	private void close()
	{
		this.dispose();
	}
	
}
