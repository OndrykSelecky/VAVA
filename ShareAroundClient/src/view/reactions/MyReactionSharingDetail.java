package view.reactions;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import entity.Reaction;
import entity.Sharing;
import view.sharings.SharingDetail;

public class MyReactionSharingDetail extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	private Sharing sharing;
	private JTextField textField_5;
	

	/**
	 * Create the frame.
	 */
	public MyReactionSharingDetail(Reaction reaction) {
		
		this.sharing=reaction.getSharing();
		setTitle("Detail mojej reakcie");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 840, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSkupina = new JLabel("Skupina:");
		lblSkupina.setBounds(26, 31, 46, 14);
		contentPane.add(lblSkupina);
		
		JLabel lblTyp = new JLabel("Typ");
		lblTyp.setBounds(26, 70, 46, 14);
		contentPane.add(lblTyp);
		
		JLabel lblPredmet = new JLabel("Predmet");
		lblPredmet.setBounds(26, 104, 46, 14);
		contentPane.add(lblPredmet);
		
		JLabel lblPopis = new JLabel("Popis");
		lblPopis.setBounds(26, 177, 46, 14);
		contentPane.add(lblPopis);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(90, 177, 213, 87);
		contentPane.add(textPane);
		textPane.setText(sharing.getDescription());
		
		textField = new JTextField();
		textField.setBounds(90, 28, 213, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(sharing.getGroup().getName());
		
		textField_1 = new JTextField();
		textField_1.setBounds(90, 67, 213, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(sharing.getType().getName());
		
		textField_2 = new JTextField();
		textField_2.setBounds(90, 101, 213, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(sharing.getLabel());
		
		textField_3 = new JTextField();
		textField_3.setBounds(90, 135, 213, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setText(sharing.getPrice());
		
		JButton btnSp = new JButton("Sp\u00E4\u0165");
		btnSp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		btnSp.setBounds(693, 230, 89, 23);
		contentPane.add(btnSp);
		
		JButton btnReagova = new JButton("Nov\u00E1 reakcia");
		btnReagova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				react();
			}
		});
		btnReagova.setBounds(568, 230, 115, 23);
		contentPane.add(btnReagova);
		
		JLabel lblCena = new JLabel("Cena");
		lblCena.setBounds(26, 138, 46, 14);
		contentPane.add(lblCena);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(326, 31, 46, 14);
		contentPane.add(lblAutor);
		
		textField_4 = new JTextField();
		textField_4.setBounds(372, 28, 160, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setText(sharing.getUser().getUserName());
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(334, 70, 198, 194);
		contentPane.add(textPane_1);
		
		JLabel lblMojaReakcia = new JLabel("Moja reakcia:");
		lblMojaReakcia.setBounds(568, 31, 94, 14);
		contentPane.add(lblMojaReakcia);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setBounds(568, 70, 207, 134);
		contentPane.add(textPane_2);
		textPane_2.setText(reaction.getMessage());
		
		textField_5 = new JTextField();
		textField_5.setBounds(696, 28, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		textField_5.setText(reaction.getDate().toString());
		
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
		
	}
	
	private void closeWindow()
	{
		this.dispose();
	}
	
	private void react()
	{
		JFrame addReactionWindow = new AddReaction(sharing);
		addReactionWindow.setVisible(true);
	}
}
