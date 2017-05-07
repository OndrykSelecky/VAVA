package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import data.Data;

public abstract class TableWindow extends JFrame {

	protected JPanel contentPane;
	protected JTable table;
	protected JScrollPane scrollPane;
	protected DefaultTableModel model;
	
	protected String[] columnNames;
	protected JButton btnSp_1;
	/**
	 * Create the frame.
	 */
	public TableWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 555, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);				
	}
	
	protected void populateTable()	
	{		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);		
		table = new JTable();
		
		addRows();
		
		table.setModel(model);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 21, 0, 0);
		table.setFillsViewportHeight(true);
		scrollPane.setSize( (int)((this.getWidth())-50), (int)(0.6*(this.getHeight())));
		contentPane.add(scrollPane);
		
		btnSp_1 = new JButton("Sp\u00E4\u0165");
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
