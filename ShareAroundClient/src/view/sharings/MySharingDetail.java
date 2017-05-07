package view.sharings;

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

import control.SharingsControl;
import entity.Sharing;
import view.reactions.AddReaction;

/**
 * Detail môjho zdie¾ania
 * @author ondryk
 *
 */
public class MySharingDetail extends JFrame {

	
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
	private JButton btnUkoniZdieanie;
	private JLabel lblStav;
	private JTextField textField_4;
	private JLabel lblDtum;
	private JTextField textField_5;
	
	
	public MySharingDetail(Sharing sharing) {
		
		this.sharing=sharing;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 566, 291);
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
		lblPopis.setBounds(327, 70, 46, 14);
		contentPane.add(lblPopis);
		
		textPane = new JTextPane();
		textPane.setBounds(327, 109, 213, 132);
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
		btnSp.setBounds(26, 218, 89, 23);
		contentPane.add(btnSp);
		
		
		lblCena = new JLabel("Cena");
		lblCena.setBounds(26, 138, 73, 14);
		contentPane.add(lblCena);
		
		btnUkoniZdieanie = new JButton("Nastavi\u0165 ako neakt\u00EDvne");
		btnUkoniZdieanie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SharingsControl.setInvalid(sharing);
				btnUkoniZdieanie.setEnabled(false);
			}
		});
		btnUkoniZdieanie.setBounds(125, 218, 178, 23);
		contentPane.add(btnUkoniZdieanie);
		
		lblStav = new JLabel("Stav");
		lblStav.setBounds(26, 181, 73, 14);
		contentPane.add(lblStav);
		
		textField_4 = new JTextField();
		textField_4.setBounds(109, 178, 194, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		lblDtum = new JLabel("D\u00E1tum");
		lblDtum.setBounds(327, 31, 46, 14);
		contentPane.add(lblDtum);
		
		textField_5 = new JTextField();
		textField_5.setBounds(395, 28, 145, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		textField_5.setText(sharing.getDate().toString());
		if (sharing.isActive() == false)
		{
			btnUkoniZdieanie.setEnabled(false);
			textField_4.setText("neaktívne");
		}
		else
		{
			textField_4.setText("aktívne");
		}
		
		
		
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
