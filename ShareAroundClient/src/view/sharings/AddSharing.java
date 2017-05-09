package view.sharings;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import control.SharingsControl;
import data.Data;
import entity.Sharing;
import utils.PropertiesWrapper;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.Timer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.annotations.common.util.impl.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;

public class AddSharing extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 263875904749288591L;

	private static final String LOCALIZATION = "locale.app";

	private JTextField priceField;
	private JTextField nameField;
	private JEditorPane dtrpnTagspane;
	private JProgressBar progressBar;

	private Timer timer = new Timer(100, new ActionListener() {

		private int counter = 1;

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (counter > 100) {
				counter = 1;
			}
			progressBar.setValue(++counter);
		}
	});

	/**
	 * Create the frame.
	 */
	public AddSharing() {
		ResourceBundle rb = ResourceBundle.getBundle(LOCALIZATION,
				Locale.forLanguageTag(PropertiesWrapper.getProperties().getProperty("locale")));

		setTitle(rb.getString("mysharings.add.title"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 400);

		JPanel lowerPanel = new JPanel();
		getContentPane().add(lowerPanel, BorderLayout.SOUTH);

		JButton backButton = new JButton(rb.getString("app.back"));
		lowerPanel.add(backButton);

		JButton addButton = new JButton(rb.getString("app.add"));
		lowerPanel.add(addButton);

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

		String[] sharingTypes = new String[Data.sharingTypes.size()];
		for (int i = 0; i < Data.sharingTypes.size(); i++) {
			sharingTypes[i] = Data.sharingTypes.get(i).getName();
		}
		JComboBox<String> typeComboBox = new JComboBox<String>(sharingTypes);
		panel.add(typeComboBox, "4, 2, fill, default");

		JLabel lblName = new JLabel(rb.getString("sharing.label"));
		panel.add(lblName, "2, 4, right, default");

		nameField = new JTextField();
		panel.add(nameField, "4, 4, fill, default");
		nameField.setColumns(10);

		JLabel lblGroup = new JLabel(rb.getString("sharing.price"));
		panel.add(lblGroup, "2, 6, right, default");

		String[] groups = new String[Data.user.getGroups().size()];
		for (int i = 0; i < Data.user.getGroups().size(); i++) {
			groups[i] = Data.user.getGroups().get(i).getName();
		}
		JComboBox<String> groupComboBox = new JComboBox<String>(groups);
		panel.add(groupComboBox, "4, 8, fill, default");

		JLabel lblPrice = new JLabel(rb.getString("sharing.group"));
		panel.add(lblPrice, "2, 8, right, default");

		priceField = new JTextField();
		panel.add(priceField, "4, 6, fill, default");
		priceField.setColumns(10);

		JLabel textLabel = new JLabel(rb.getString("sharing.desc"));
		panel.add(textLabel, "2, 10, right, default");

		JEditorPane descriptionPane = new JEditorPane();
		panel.add(descriptionPane, "4, 10, fill, fill");

		JLabel lblPublicData = new JLabel(rb.getString("sharing.shared_data"));
		panel.add(lblPublicData, "2, 12");

		JCheckBox addressCheckBox = new JCheckBox(rb.getString("user.address"));
		panel.add(addressCheckBox, "4, 12");

		JCheckBox emailCheckBox = new JCheckBox(rb.getString("user.email"));
		panel.add(emailCheckBox, "4, 14");

		JCheckBox telephoneCheckBox = new JCheckBox(rb.getString("user.telephone"));
		panel.add(telephoneCheckBox, "4, 16");

		JLabel lblTags = new JLabel(rb.getString("sharing.tags"));
		panel.add(lblTags, "2, 18, right, default");

		dtrpnTagspane = new JEditorPane();
		panel.add(dtrpnTagspane, "4, 18");

		JPanel rightPanel = new JPanel();
		leftRightPane.setRightComponent(rightPanel);
		rightPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel();
		rightPanel.add(lblNewLabel, BorderLayout.CENTER);

		JButton openButton = new JButton(rb.getString("mysharings.addPhoto"));
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showDialog(rightPanel, rb.getString("app.choose"));

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					timer.start();

					File selectedFile = fc.getSelectedFile();

					try {
						String extension = FilenameUtils.getExtension(selectedFile.getAbsolutePath());
						BufferedImage myOriginalPicture = ImageIO.read(selectedFile);

						Image previewScaledImage = getScaledImage(myOriginalPicture, lblNewLabel.getWidth(),
								lblNewLabel.getHeight());

						sendForTagging(myOriginalPicture);

						lblNewLabel.setIcon(new ImageIcon(previewScaledImage));

						timer.stop();
						progressBar.setValue(100);
					} catch (IOException e1) {
						// TODO Logging
					}
				}
			}
		});
		rightPanel.add(openButton, BorderLayout.NORTH);

		progressBar = new JProgressBar();
		rightPanel.add(progressBar, BorderLayout.SOUTH);

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sharing sharing = new Sharing();
				sharing.setDescription(descriptionPane.getText());
				sharing.setGroup(Data.user.getGroups().get(groupComboBox.getSelectedIndex()));
				sharing.setLabel(nameField.getText());
				sharing.setPrice(priceField.getText());
				sharing.setType(Data.sharingTypes.get(typeComboBox.getSelectedIndex()));
				sharing.setUser(Data.user);
				sharing.setShowAddress(addressCheckBox.isSelected());
				sharing.setShowEmail(emailCheckBox.isSelected());
				sharing.setShowPhone(telephoneCheckBox.isSelected());
				SharingsControl.addNewSharing(sharing);
				close();
			}
		});
	}

	private void close() {
		this.dispose();
	}

	private Image getScaledImage(BufferedImage original, int maxWidth, int maxHeight) {
		int newWidth = original.getWidth();
		int newHeight = original.getHeight();

		if (original.getWidth() > maxWidth) {
			newWidth = maxWidth;
			newHeight = (newWidth * original.getHeight()) / original.getWidth();
		}

		if (newHeight > maxHeight) {
			newHeight = maxHeight;
			newWidth = (newHeight * original.getWidth()) / original.getHeight();
		}

		return original.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
	}

	private BufferedImage createBufferedImage(Image original) {
		BufferedImage bimage = new BufferedImage(original.getWidth(null), original.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(original, 0, 0, null);
		bGr.dispose();

		return bimage;
	}

	private void sendForTagging(BufferedImage original) {

		Image uploadScaledImage = getScaledImage(original, 800, 600);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(createBufferedImage(uploadScaledImage), "jpg", baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] bytes = baos.toByteArray();

		String encoded = Base64.getEncoder().encodeToString(bytes);

		SharingsControl.getTagsOfImage(encoded);
		dtrpnTagspane.setText(String.join(", ", Data.tags));

	}

}
