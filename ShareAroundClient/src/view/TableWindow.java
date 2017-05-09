package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import data.Data;
import utils.PropertiesWrapper;

public abstract class TableWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7453114091620212004L;

	private static final String LOCALIZATION = "locale.app";

	protected JPanel contentPane;
	protected JTable table;
	protected JScrollPane scrollPane;
	protected DefaultTableModel model;
	
	protected String[] columnNames;
	protected JButton btnSp_1;
	
	protected ResourceBundle rb;

	/**
	 * Create the frame.
	 */
	public TableWindow() {		
		rb = ResourceBundle.getBundle(LOCALIZATION,
				Locale.forLanguageTag(PropertiesWrapper.getProperties().getProperty("locale")));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 555, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	protected void populateTable()	
	{		
		model = new DefaultTableModel() {

			private static final long serialVersionUID = -7453114091120212001L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		
		model.setColumnIdentifiers(columnNames);		
		table = new JTable();
		
		addRows();
				
		table.setModel(model);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 21, 0, 0);
		table.setFillsViewportHeight(true);
		scrollPane.setSize( (int)((this.getWidth())-50), (int)(0.6*(this.getHeight())));
		contentPane.add(scrollPane);
		
		btnSp_1 = new JButton(rb.getString("app.back"));
		btnSp_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnSp_1.setBounds(412, 242, 89, 23);
		getContentPane().add(btnSp_1);
		
	}
	
	protected abstract void addRows();
	
	protected void close()
	{
		
		this.dispose();
	}


}
