package utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Operations on images.
 * 
 * @author thecodecook
 *
 */
public class ImageUtils {
	
	/**
	 * Returns scaled image with specified max width and max height
	 * 
	 * @param original
	 *            The original image
	 * @param maxWidth
	 *            Max width of the resulting image
	 * @param maxHeight
	 *            Max height of the resulting image
	 * @return Scaled image
	 */
	public static Image getScaledImage(BufferedImage original, int maxWidth, int maxHeight) {
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

	/**
	 * Converts {@link Image} into {@link BufferedImage} by redrawing it.
	 * 
	 * @param original
	 *            The original image.
	 * @return Redrawn image of correct type.
	 */
	public static BufferedImage createBufferedImage(Image original) {
		BufferedImage bimage = new BufferedImage(original.getWidth(null), original.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(original, 0, 0, null);
		bGr.dispose();

		return bimage;
	}
}
