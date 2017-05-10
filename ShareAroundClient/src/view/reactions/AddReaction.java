package view.reactions;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import control.ReactionsControl;
import data.Data;
import entity.Reaction;
import entity.Sharing;
import utils.PropertiesWrapper;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

/**
 * The view for adding a reaction.
 * 
 * @author ondryk
 * @author thecodecook
 *
 */
public class AddReaction extends JFrame {

	private static final long serialVersionUID = -8855959302405873196L;
	private static final String LOCALIZATION = "locale.app";

	private JCheckBox chckbxAddress;
	private JCheckBox chckbxTelephone;
	private JCheckBox chckbxEmail;

	private Sharing sharing;

	/**
	 * Create the frame.
	 */
	public AddReaction(Sharing sharing) {
		ResourceBundle rb = ResourceBundle.getBundle(LOCALIZATION,
				Locale.forLanguageTag(PropertiesWrapper.getProperties().getProperty("locale")));

		this.sharing=sharing;
		
		setTitle(rb.getString("addsharing.title"));
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 300);
		
		
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,}));
		
		JLabel message = new JLabel(rb.getString("reaction.message"));
		getContentPane().add(message, "2, 2, 1, 9");
		
		JTextPane textPane = new JTextPane();
		getContentPane().add(textPane, "4, 2, 5, 10, fill, fill");
		
		JLabel lblSharedata = new JLabel(rb.getString("sharing.shared_data"));
		getContentPane().add(lblSharedata, "2, 14");
		
		chckbxAddress = new JCheckBox(rb.getString("user.address"));
		getContentPane().add(chckbxAddress, "4, 14");
		
		chckbxTelephone = new JCheckBox(rb.getString("user.telephone"));
		getContentPane().add(chckbxTelephone, "6, 14");
		
		chckbxEmail = new JCheckBox(rb.getString("user.email"));
		getContentPane().add(chckbxEmail, "8, 14, left, default");
		
		JButton btnSend = new JButton(rb.getString("app.send"));
		getContentPane().add(btnSend, "6, 16");
		
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reaction reaction = new Reaction();
				reaction.setMessage(textPane.getText());
				reaction.setShowAddress(chckbxAddress.isSelected());
				reaction.setShowEmail(chckbxEmail.isSelected());
				reaction.setShowPhone(chckbxTelephone.isSelected());
				reaction.setSharing(sharing);
				reaction.setUser(Data.user);
				
				ReactionsControl.addNewReaction(reaction);
				
				close();
			}
		});
	}
	
	
	
	private void close()
	{
		this.dispose();
	}
	
}
