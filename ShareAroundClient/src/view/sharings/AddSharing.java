package view.sharings;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.SharingsControl;
import data.Data;
import entity.Sharing;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class AddSharing extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtZatiaNeimplementovan;
	private JTextField textField_1;

	
	/**
	 * Create the frame.
	 */
	public AddSharing() {
		setTitle("Prida\u0165 zdie\u013Eanie");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTyp = new JLabel("Typ:");
		lblTyp.setBounds(23, 24, 46, 14);
		contentPane.add(lblTyp);
		
		
		String[] sharingTypes = new String[Data.sharingTypes.size()];	
		
		for (int i=0; i< Data.sharingTypes.size(); i++)
		{
			sharingTypes[i] = Data.sharingTypes.get(i).getName();			
		}		
		
		JComboBox comboBox = new JComboBox(sharingTypes);
		comboBox.setBounds(91, 21, 117, 20);
		contentPane.add(comboBox);
		
		
		
		JLabel lblPredmet = new JLabel("Predmet");
		lblPredmet.setBounds(23, 63, 46, 14);
		contentPane.add(lblPredmet);
		
		textField = new JTextField();
		textField.setBounds(91, 60, 117, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblSkupina = new JLabel("Skupina");
		lblSkupina.setBounds(23, 103, 46, 14);
		contentPane.add(lblSkupina);
		
		
		String[] groups = new String[Data.user.getGroups().size()];	
		
		for (int i=0; i< Data.user.getGroups().size(); i++)
		{
			groups[i] = Data.user.getGroups().get(i).getName();	
		}	
		
		JComboBox comboBox_1 = new JComboBox(groups);
		comboBox_1.setBounds(91, 100, 117, 20);
		contentPane.add(comboBox_1);
		
		
		JLabel lblText = new JLabel("Text");
		lblText.setBounds(239, 24, 46, 14);
		contentPane.add(lblText);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(239, 49, 185, 96);
		contentPane.add(textPane);
		
		JLabel lblTagy = new JLabel("Tagy");
		lblTagy.setBounds(23, 174, 46, 14);
		contentPane.add(lblTagy);
		
		txtZatiaNeimplementovan = new JTextField();
		txtZatiaNeimplementovan.setText("Zatia\u013E som neurobil");
		txtZatiaNeimplementovan.setBounds(91, 171, 117, 21);
		contentPane.add(txtZatiaNeimplementovan);
		txtZatiaNeimplementovan.setColumns(10);
		
				
		JButton btnSp = new JButton("Sp\u00E4\u0165");
		btnSp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnSp.setBounds(23, 227, 89, 23);
		contentPane.add(btnSp);
		
		JLabel lblCena = new JLabel("Cena");
		lblCena.setBounds(23, 139, 46, 14);
		contentPane.add(lblCena);
		
		textField_1 = new JTextField();
		textField_1.setBounds(91, 140, 117, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JRadioButton rdbtnAdresa = new JRadioButton("Adresa");
		rdbtnAdresa.setBounds(239, 187, 109, 23);
		contentPane.add(rdbtnAdresa);
		
		JRadioButton rdbtnEmail = new JRadioButton("Email");
		rdbtnEmail.setBounds(239, 207, 109, 23);
		contentPane.add(rdbtnEmail);
		
		JRadioButton rdbtnTelefnneslo = new JRadioButton("Telef\u00F3nne \u010D\u00EDslo");
		rdbtnTelefnneslo.setBounds(239, 227, 109, 23);
		contentPane.add(rdbtnTelefnneslo);
		
		JLabel lblPoskytndaje = new JLabel("Poskytn\u00FA\u0165 \u00FAdaje:");
		lblPoskytndaje.setBounds(239, 174, 117, 14);
		contentPane.add(lblPoskytndaje);
		
		JButton btnPrida = new JButton("Prida\u0165 zdie\u013Eanie");
		btnPrida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Sharing sharing = new Sharing();
				sharing.setDescription(textPane.getText());
				sharing.setGroup(Data.user.getGroups().get(comboBox_1.getSelectedIndex()));
				sharing.setLabel(textField.getText());
				sharing.setPrice(textField_1.getText());
				sharing.setType(Data.sharingTypes.get(comboBox.getSelectedIndex()));
				sharing.setUser(Data.user);
				sharing.setShowAddress(rdbtnAdresa.isSelected());
				sharing.setShowEmail(rdbtnEmail.isSelected());
				sharing.setShowPhone(rdbtnTelefnneslo.isSelected());
				SharingsControl.addNewSharing(sharing);
				close();
			}
		});
		btnPrida.setBounds(122, 227, 109, 23);
		contentPane.add(btnPrida);
	}
	
	private void close()
	{
		this.dispose();
	}
	
}
