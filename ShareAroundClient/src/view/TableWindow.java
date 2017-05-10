package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import utils.PropertiesWrapper;

/**
 * Base class for many of our views.
 * 
 * @author ondryk
 * @author thecodecook
 *
 */
public abstract class TableWindow extends JFrame {

	private static final long serialVersionUID = 7453114091620212004L;
	private static final String LOCALIZATION = "locale.app";

	protected JPanel contentPane;
	protected JPanel panel;
	protected JTable table;
	protected JScrollPane scrollPane;
	protected DefaultTableModel model;
	protected JPanel sidePanel;

	protected String[] columnNames;
	protected JButton btnBack;

	protected ResourceBundle rb;

	/**
	 * Create the frame.
	 */
	public TableWindow() {
		rb = ResourceBundle.getBundle(LOCALIZATION,
				Locale.forLanguageTag(PropertiesWrapper.getProperties().getProperty("locale")));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 555, 396);

		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Filter", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, }));

		sidePanel = new JPanel();
		getContentPane().add(sidePanel, BorderLayout.EAST);
		sidePanel.setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec.decode("119px"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, }));

		JButton btnBack = new JButton(rb.getString("app.back"));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		sidePanel.add(btnBack, "1, 16, default, bottom");

	}

	protected void populateTable() {
		model = new DefaultTableModel() {

			private static final long serialVersionUID = -7453114091120212001L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		model.setColumnIdentifiers(columnNames);
		addRows();

		table = new JTable(model);

		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

	protected abstract void addRows();

	protected void close() {
		this.dispose();
	}

}
