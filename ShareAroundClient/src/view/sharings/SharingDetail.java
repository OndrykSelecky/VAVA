package view.sharings;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import entity.Sharing;
import view.reactions.AddReaction;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SharingDetail extends JFrame {

	
	private JTextField textField_4;
	protected JPanel contentPane;
	protected JTextField textField;
	protected JTextField textField_1;
	protected JTextField textField_2;
	protected JTextField textField_3;
	protected JLabel lblSkupina;
	protected JLabel lblTyp;
	protected JLabel lblPredmet;
	protected JLabel lblPopis;
	protected JTextPane textPane;
	protected JButton btnSp;
	protected JLabel lblCena;

	protected Sharing sharing;
	

	/**
	 * Create the frame.
	 */
	public SharingDetail(Sharing sharing) {
		
		
		setTitle("Detail zdie¾ania");
		
this.sharing=sharing;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 566, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Detail zdie¾ania");
		
		lblSkupina = new JLabel("Skupina:");
		lblSkupina.setBounds(26, 31, 73, 14);
		contentPane.add(lblSkupina);
		
		lblTyp = new JLabel("Typ");
		lblTyp.setBounds(26, 70, 46, 14);
		contentPane.add(lblTyp);
		
		lblPredmet = new JLabel("Predmet");
		lblPredmet.setBounds(26, 104, 73, 14);
		contentPane.add(lblPredmet);
		
		lblPopis = new JLabel("Popis");
		lblPopis.setBounds(26, 163, 46, 14);
		contentPane.add(lblPopis);		

		lblCena = new JLabel("Cena");
		lblCena.setBounds(26, 138, 73, 14);
		contentPane.add(lblCena);
		
		textPane = new JTextPane();
		textPane.setBounds(26, 191, 277, 100);
		contentPane.add(textPane);
		textPane.setText(sharing.getDescription());
		
		textField = new JTextField();
		textField.setBounds(109, 28, 194, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(sharing.getGroup().getName());
		
		textField_1 = new JTextField();
		textField_1.setBounds(109, 67, 194, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(sharing.getType().getName());
		
		textField_2 = new JTextField();
		textField_2.setBounds(109, 101, 194, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(sharing.getLabel());
		
		textField_3 = new JTextField();
		textField_3.setBounds(109, 135, 194, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setText(sharing.getPrice());
		
		btnSp = new JButton("Sp\u00E4\u0165");
		btnSp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		btnSp.setBounds(334, 235, 89, 23);
		contentPane.add(btnSp);
		
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(326, 31, 46, 14);
		contentPane.add(lblAutor);
		
		textField_4 = new JTextField();
		textField_4.setBounds(372, 28, 132, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setText(sharing.getUser().getUserName());
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(328, 84, 194, 140);
		contentPane.add(textPane_1);
		
		//Read data about author into text pane
		Document document = textPane_1.getDocument();	     		
		if (sharing.getShowAddress() == true)
		{
			String s = "Adresa:\n" + sharing.getUser().getAddress().getStreetAddress()
					+ " " + sharing.getUser().getAddress().getHouseNumber() + "\n"
					+ sharing.getUser().getAddress().getCity() + " " + sharing.getUser().getAddress().getZipCode()
					+ "\n" + sharing.getUser().getAddress().getState() + "\n";
			try {
				document.insertString(document.getLength(), s , null);
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (sharing.getShowEmail() == true)
		{
			String s = "Email:\n" + sharing.getUser().getEmail() + "\n";
			try {
				document.insertString(document.getLength(), s , null);
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (sharing.getShowEmail() == true)
		{
			String s = "Tel. èíslo:\n" + sharing.getUser().getPhone() + "\n";
			try {
				document.insertString(document.getLength(), s , null);
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		JButton btnReagova = new JButton("Reagova\u0165");
		btnReagova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				react();
			}
		});
		btnReagova.setBounds(433, 235, 89, 23);
		contentPane.add(btnReagova);
		
		
	}
	
	protected void closeWindow()
	{
		this.dispose();
	}
	
	protected void react()
	{
		JFrame addReactionWindow = new AddReaction(sharing);
		addReactionWindow.setVisible(true);
	}

}
