package view.sharings;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import control.SharingsControl;
import entity.Sharing;
import entity.Tag;
import utils.ImageUtils;
import utils.PropertiesWrapper;
import view.reactions.AddReaction;

/**
 * The view for displaying detail of my sharing.
 * 
 * @author ondryk
 * @author thecodecook
 *
 */
public class MySharingDetail extends JFrame {

	private static final long serialVersionUID = 339700762358955556L;
	private static final String LOCALIZATION = "locale.app";
	private static Logger LOG = Logger.getLogger(MySharingDetail.class.getName());

	protected Sharing sharing;
	private JTextField priceField;
	private JTextField nameField;
	private JEditorPane dtrpnTagspane;

	public MySharingDetail(Sharing sharing, MySharings parent) {

		this.sharing = sharing;

		ResourceBundle rb = ResourceBundle.getBundle(LOCALIZATION,
				Locale.forLanguageTag(PropertiesWrapper.getProperties().getProperty("locale")));

		setTitle(rb.getString("sharingdetail.title"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 400);

		JPanel lowerPanel = new JPanel();
		getContentPane().add(lowerPanel, BorderLayout.SOUTH);

		JButton backButton = new JButton(rb.getString("app.back"));
		lowerPanel.add(backButton);

		JButton inactivateButton = new JButton(rb.getString("sharingdetail.inactivate"));
		lowerPanel.add(inactivateButton);

		JPanel upperPanel = new JPanel();
		getContentPane().add(upperPanel, BorderLayout.CENTER);
		upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.X_AXIS));

		JSplitPane leftRightPane = new JSplitPane();
		upperPanel.add(leftRightPane);

		JPanel panel = new JPanel();
		leftRightPane.setLeftComponent(panel);
		panel.setMinimumSize(new Dimension(300, 50));
		panel.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblType = new JLabel(rb.getString("sharing.type"));
		panel.add(lblType, "2, 2, right, default");

		JTextField typeValue = new JTextField(sharing.getType().getName());
		panel.add(typeValue, "4, 2, fill, default");
		typeValue.setEditable(false);

		JLabel lblName = new JLabel(rb.getString("sharing.label"));
		panel.add(lblName, "2, 4, right, default");

		nameField = new JTextField(sharing.getLabel());
		panel.add(nameField, "4, 4, fill, default");
		nameField.setColumns(10);
		nameField.setEditable(false);

		JLabel lblGroup = new JLabel(rb.getString("sharing.price"));
		panel.add(lblGroup, "2, 6, right, default");

		JTextField state = new JTextField(
				sharing.isActive() ? rb.getString("sharing.state.active") : rb.getString("sharing.state.inactive"));
		panel.add(state, "4, 8, fill, default");
		state.setEditable(false);

		JLabel lblPrice = new JLabel(rb.getString("sharing.state"));
		panel.add(lblPrice, "2, 8, right, default");

		priceField = new JTextField(sharing.getPrice());
		panel.add(priceField, "4, 6, fill, default");
		priceField.setColumns(10);
		priceField.setEditable(false);

		JLabel textLabel = new JLabel(rb.getString("sharing.desc"));
		panel.add(textLabel, "2, 10, right, default");

		JEditorPane descriptionPane = new JEditorPane();
		descriptionPane.setText(sharing.getDescription());
		panel.add(descriptionPane, "4, 10, fill, fill");
		descriptionPane.setEditable(false);

		JLabel lblPublicData = new JLabel(rb.getString("sharing.shared_data"));
		panel.add(lblPublicData, "2, 12");

		JCheckBox addressCheckBox = new JCheckBox(rb.getString("user.address"));
		addressCheckBox.setSelected(sharing.getShowAddress());
		panel.add(addressCheckBox, "4, 12");
		addressCheckBox.setEnabled(false);

		JCheckBox emailCheckBox = new JCheckBox(rb.getString("user.email"));
		panel.add(emailCheckBox, "4, 14");
		emailCheckBox.setSelected(sharing.getShowEmail());
		emailCheckBox.setEnabled(false);

		JCheckBox telephoneCheckBox = new JCheckBox(rb.getString("user.telephone"));
		panel.add(telephoneCheckBox, "4, 16");
		telephoneCheckBox.setSelected(sharing.getShowPhone());
		telephoneCheckBox.setEnabled(false);

		JLabel lblTags = new JLabel(rb.getString("sharing.tags"));
		panel.add(lblTags, "2, 18, right, default");

		dtrpnTagspane = new JEditorPane();
		panel.add(dtrpnTagspane, "4, 18");
		dtrpnTagspane.setEditable(false);

		StringBuffer sb = new StringBuffer();
		String[] arrTagNames = new String[sb.length()];
		for (Tag tag : sharing.getTags()) {
			sb.append(tag.getText()).append(", ");
		}
		dtrpnTagspane.setText(sb.delete(sb.length() - 2, sb.length()).toString());

		JLabel lblNewLabel = new JLabel();
		leftRightPane.setRightComponent(lblNewLabel);

		String encodedImage = sharing.getEncodedImage();
		if (encodedImage != null) {
			try {

				Image previewScaledImage = ImageUtils.getScaledImage(decodeImage(encodedImage), 320, 320);
				lblNewLabel.setIcon(new ImageIcon(previewScaledImage));

			} catch (IOException err) {
				LOG.log(Level.WARNING, "Image conversion failed from BASE64.", err);
			}
		}

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});

		inactivateButton.setEnabled(sharing.isActive());
		inactivateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SharingsControl.setInvalid(sharing);
				state.setText(rb.getString("sharing.state.inactive"));
				inactivateButton.setEnabled(false);

				parent.refreshData();
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

	private BufferedImage decodeImage(String base64Encoded) throws IOException {
		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Encoded);
		return ImageIO.read(new ByteArrayInputStream(imageBytes));
	}

}
