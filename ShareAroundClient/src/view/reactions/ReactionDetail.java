package view.reactions;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import entity.Reaction;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReactionDetail extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	
	public ReactionDetail(Reaction reaction) {
		setTitle("Detail reakcie");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 486, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(257, 28, 46, 14);
		contentPane.add(lblAutor);
		
		textField = new JTextField();
		textField.setBounds(313, 25, 134, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(reaction.getUser().getUserName());
		
		JLabel lblDtum = new JLabel("D\u00E1tum");
		lblDtum.setBounds(30, 28, 46, 14);
		contentPane.add(lblDtum);
		
		textField_1 = new JTextField();
		textField_1.setBounds(95, 25, 123, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(reaction.getDate().toString());
		
		JLabel lblSprva = new JLabel("Spr\u00E1va");
		lblSprva.setBounds(30, 72, 46, 14);
		contentPane.add(lblSprva);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(30, 101, 189, 111);
		contentPane.add(textPane);
		textPane.setText(reaction.getMessage());
		
		JLabel lblKontaktndaje = new JLabel("Kontaktn\u00E9 \u00FAdaje");
		lblKontaktndaje.setBounds(257, 72, 87, 14);
		contentPane.add(lblKontaktndaje);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(257, 101, 190, 111);
		contentPane.add(textPane_1);
		
		Document document = textPane_1.getDocument();	     		
		if (reaction.getShowAddress() == true)
		{
			String s = "Adresa:\n" + reaction.getUser().getAddress().getStreetAddress()
					+ " " + reaction.getUser().getAddress().getHouseNumber() + "\n"
					+ reaction.getUser().getAddress().getCity() + " " + reaction.getUser().getAddress().getZipCode()
					+ "\n" + reaction.getUser().getAddress().getState() + "\n";
			try {
				document.insertString(document.getLength(), s , null);
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (reaction.getShowEmail() == true)
		{
			String s = "Email:\n" + reaction.getUser().getEmail() + "\n";
			try {
				document.insertString(document.getLength(), s , null);
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (reaction.getShowEmail() == true)
		{
			String s = "Tel. èíslo:\n" + reaction.getUser().getPhone() + "\n";
			try {
				document.insertString(document.getLength(), s , null);
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnOk.setBounds(188, 227, 89, 23);
		contentPane.add(btnOk);
	}
	
	private void close()
	{
		this.dispose();
	}

}
