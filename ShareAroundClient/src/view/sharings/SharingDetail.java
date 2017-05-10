package view.sharings;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import entity.Sharing;
import utils.PropertiesWrapper;
import view.reactions.AddReaction;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

/**
 * The view for displaying detail of sharing.
 * 
 * @author ondryk
 * @author thecodecook
 *
 */
public class SharingDetail extends JFrame {

	private static final long serialVersionUID = -8551730565896151747L;
	private static final String LOCALIZATION = "locale.app";

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
		ResourceBundle rb = ResourceBundle.getBundle(LOCALIZATION,
				Locale.forLanguageTag(PropertiesWrapper.getProperties().getProperty("locale")));

		setTitle(MessageFormat.format(rb.getString("sharingdetail.title"), sharing.getLabel()));

		this.sharing = sharing;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 566, 334);

		getContentPane().setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("left:pref:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, }));

		JLabel lblName = new JLabel(sharing.getLabel());
		lblName.setFont(new Font("Tahoma", Font.BOLD, 20));
		getContentPane().add(lblName, "2, 2");

		JLabel lblType = new JLabel(rb.getString("sharing.type"));
		lblType.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblType, "2, 4");

		JLabel lbltypeValue = new JLabel(sharing.getType().getName());
		getContentPane().add(lbltypeValue, "4, 4");

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), rb.getString("sharing.author"),
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panel, "6, 4, 8, 7, fill, fill");
		panel.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), FormSpecs.RELATED_GAP_ROWSPEC, }));

		JLabel lblNewLabel = new JLabel(sharing.getUser().getUserName());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel, "2, 2");

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		panel.add(textPane_1, "2, 4, fill, fill");

		JLabel lblPricelabel = new JLabel(rb.getString("sharing.price"));
		lblPricelabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblPricelabel, "2, 6");

		JLabel lblPricevalue = new JLabel(sharing.getPrice());
		getContentPane().add(lblPricevalue, "4, 6");

		JLabel lblGroplabel = new JLabel(rb.getString("sharing.group"));
		lblGroplabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblGroplabel, "2, 8");

		JLabel lblGroupvalue = new JLabel(sharing.getGroup().getName());
		getContentPane().add(lblGroupvalue, "4, 8");

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		getContentPane().add(textPane, "2, 10, 3, 1, fill, fill");
		textPane.setText(sharing.getDescription());

		JButton btnReact = new JButton(rb.getString("sharing.react"));
		getContentPane().add(btnReact, "4, 12, right, top");

		StringBuffer userDesc = new StringBuffer();

		if (sharing.getShowAddress() == true) {
			userDesc.append(rb.getString("user.address")).append(":\n")
					.append(sharing.getUser().getAddress().getStreetAddress()).append(" ")
					.append(sharing.getUser().getAddress().getHouseNumber()).append("\n")
					.append(sharing.getUser().getAddress().getCity()).append(" ")
					.append(sharing.getUser().getAddress().getZipCode()).append("\n")
					.append(sharing.getUser().getAddress().getState()).append("\n");
		}

		if (sharing.getShowEmail() == true) {
			userDesc.append(rb.getString("user.email")).append(":\n").append(sharing.getUser().getEmail()).append("\n");
		}

		if (sharing.getShowEmail() == true) {
			userDesc.append(rb.getString("user.telephone")).append(":\n").append(sharing.getUser().getPhone())
					.append("\n");
		}
		
		textPane_1.setText(userDesc.toString());

		btnReact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				react();
			}
		});

	}

	protected void closeWindow() {
		this.dispose();
	}

	protected void react() {
		JFrame addReactionWindow = new AddReaction(sharing);
		addReactionWindow.setVisible(true);
	}

}
