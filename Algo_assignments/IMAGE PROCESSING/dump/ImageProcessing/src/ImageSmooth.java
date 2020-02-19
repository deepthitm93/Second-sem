/************************************************************
 * Image Gray Scale
 * Pass Average Red+Green+Blue to all the Pixel in the Image 
 * 
 * 
 * 
 * 
 *Designed by Ganga Poudel and Deepthi TM
 ************************************************************ 
 */

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class ImageSmooth extends JFrame {
	private static final long serialVersionUID = 1L;
	static int[][][] rgb_buffer;

	ImageSmooth(String in, String out) {
		super("**Image Smoothing**");
		try {
			this.setLayout(new GridLayout(1, 2, 10, 10));
			JPanel img1 = new JPanel();
			JPanel img2 = new JPanel();
			File f1 = new File(in);
			File f2 = new File(out);
			BufferedImage image1 = ImageIO.read(f1);
			img1.add(new JLabel(new ImageIcon(image1)));
			BufferedImage image2 = Imgsmooth(image1);
			ImageIO.write(image2, "jpg", f2);
			img2.add(new JLabel(new ImageIcon(image2)));
			this.add(img1);
			this.add(img2);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	// convert to Smooth
	BufferedImage Imgsmooth(BufferedImage image) {
		// TODO Auto-generated method stub
		BufferedImage SmoothPic = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

		rgb_buffer = new int[3][image.getHeight()][image.getWidth()];
		for (int row = 0; row < image.getHeight(); row++) {
			for (int col = 0; col < image.getWidth(); col++) {
				Color c = new Color(image.getRGB(col, row));
				/*int red = (int) c.getRed();
				int green = (int) c.getGreen();
				int blue = (int) c.getBlue();*/
	        	 rgb_buffer[0][row][col] =c.getRed();
	        	 rgb_buffer[1][row][col] =c.getGreen();
	        	 rgb_buffer[2][row][col] =c.getBlue();

			}
		}
		for (int x = 1; x < image.getHeight() - 1; x++) {
			for (int y = 1; y < image.getWidth() - 1; y++) {

				int red = rgb_buffer[0][x - 1][y - 1] + rgb_buffer[0][x - 1][y] + rgb_buffer[0][x - 1][y + 1]
						+ rgb_buffer[0][x][y - 1] + rgb_buffer[0][x][y] + rgb_buffer[0][x][y + 1]
						+ rgb_buffer[0][x + 1][y - 1] + rgb_buffer[0][x + 1][y] + rgb_buffer[0][x + 1][y + 1];

				int green = rgb_buffer[1][x - 1][y - 1] + rgb_buffer[1][x - 1][y] + rgb_buffer[1][x - 1][y + 1]
						+ rgb_buffer[1][x][y - 1] + rgb_buffer[1][x][y] + rgb_buffer[1][x][y + 1]
						+ rgb_buffer[1][x + 1][y - 1] + rgb_buffer[1][x + 1][y] + rgb_buffer[1][x + 1][y + 1];

				int blue = rgb_buffer[2][x - 1][y - 1] + rgb_buffer[2][x - 1][y] + rgb_buffer[2][x - 1][y + 1]
						+ rgb_buffer[2][x][y - 1] + rgb_buffer[2][x][y] + rgb_buffer[2][x][y + 1]
						+ rgb_buffer[2][x + 1][y - 1] + rgb_buffer[2][x + 1][y] + rgb_buffer[2][x + 1][y + 1];

				Color c2 = new Color(red / 9, green / 9, blue / 9);
				SmoothPic.setRGB(y, x, c2.getRGB());
			}
		}
		return SmoothPic;
	}

	public static void main(String[] args) throws IOException {

		ImageSmooth Smooth = new ImageSmooth(
				"C:\\DEEPTHI\\Masters\\2nd sem\\Algorithms\\Assignments\\IMAGE PROCESSING\\dump\\ImageProcessing\\src\\rezied.jpg", "output");
		Smooth.setSize(800, 600);
		Smooth.setVisible(true);
		Smooth.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}// class ends here