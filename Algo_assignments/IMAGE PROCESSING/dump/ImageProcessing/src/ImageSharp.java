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


public class ImageSharp extends JFrame{
	private static final long serialVersionUID = 1L;
	static int[][][] rgb_buffer;

	ImageSharp(String in, String out) {
		super("**Sharping**");
		try {
			this.setLayout(new GridLayout(1, 2, 10, 10));
			JPanel img1 = new JPanel();
			JPanel img2 = new JPanel();
			File f1 = new File(in);
			File f2 = new File(out);
			BufferedImage image1 = ImageIO.read(f1);
			img1.add(new JLabel(new ImageIcon(image1)));
			BufferedImage image2 = SharpProcess(image1);
			ImageIO.write(image2, "jpg", f2);
			img2.add(new JLabel(new ImageIcon(image2)));
			this.add(img1);
			this.add(img2);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
    
    
    BufferedImage SharpProcess(BufferedImage image) {
		// TODO Auto-generated method stub
    		BufferedImage sharpic = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

    		rgb_buffer = new int[3][image.getHeight()][image.getWidth()];
    		for (int row = 0; row < image.getHeight(); row++) {
    			for (int col = 0; col < image.getWidth(); col++) {
                    Color c = new Color(image.getRGB(col, row));
                    rgb_buffer[0][row][col] = c.getRed();
    				rgb_buffer[1][row][col] = c.getGreen();
    				rgb_buffer[2][row][col] = c.getBlue();
    			}
    		}
    			for (int row = 1; row < image.getHeight() - 1; row++) {
    				for (int col = 1; col < image.getWidth() - 1; col++) {
    					int red = 0, green = 0, blue = 0;
    					red = (int) (rgb_buffer[0][row][col] + 5 * (rgb_buffer[0][row][col] - rgb_buffer[0][row - 1][col - 1]));
    					green = (int) (rgb_buffer[1][row][col] + 5 * (rgb_buffer[1][row][col] - rgb_buffer[1][row - 1][col - 1]));
    					blue = (int) (rgb_buffer[2][row][col] + 5 * (rgb_buffer[2][row][col] - rgb_buffer[2][row - 1][col - 1]));
                    
    					if (red > 255) {
    						red = 255;
    					}
    					if (red < 0) {
    						red = 0;
    					}
    					if (green > 255) {
    						green = 255;
    					}
    					if (green < 0) {
    						green = 0;
    					}

    					if (blue > 255) {
    						blue = 255;
    					}
    					if (blue < 0) {
    						blue = 0;
    					}	
    				Color c2 = new Color(red, green, blue);
    				sharpic.setRGB(col, row, c2.getRGB());
    				}

    	}
				return sharpic;}


	 public static void main(String[] args) throws IOException 
	 {
   	 
		 ImageSharp   boundimg = new ImageSharp ("C:\\DEEPTHI\\Masters\\2nd sem\\Algorithms\\Assignments\\IMAGE PROCESSING\\dump\\ImageProcessing\\src\\rezied.jpg", "output");
		 boundimg.setSize(800, 600);
		 boundimg.setVisible(true);
		 boundimg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	
	   
	    }	
}//class ends here