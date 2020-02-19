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


public class ImageGrayscale extends JFrame{
	private static final long serialVersionUID = 1L;
	static int[][][] rgb_buffer;

	ImageGrayscale(String in, String out) {
		super("**GRAY SCALE**");
		try {
			this.setLayout(new GridLayout(1, 2, 10, 10));
			JPanel img1 = new JPanel();
			JPanel img2 = new JPanel();
			File f1 = new File(in);
			File f2 = new File(out);
			BufferedImage image1 = ImageIO.read(f1);
			img1.add(new JLabel(new ImageIcon(image1)));
			BufferedImage image2 = GrayscaleProcess(image1);
			ImageIO.write(image2, "jpg", f2);
			img2.add(new JLabel(new ImageIcon(image2)));
			this.add(img1);
			this.add(img2);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
    //get image width and height
    //int width = img.getWidth();
    //int height = img.getHeight();

    //convert to grayscale
    BufferedImage GrayscaleProcess(BufferedImage image) {
		// TODO Auto-generated method stub
    		BufferedImage graypic = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

    		rgb_buffer = new int[3][image.getHeight()][image.getWidth()];
    		for (int row = 0; row < image.getHeight(); row++) {
    			for (int col = 0; col < image.getWidth(); col++) {
                    Color c = new Color(image.getRGB(col, row));
                    int red = (int) (c.getRed() * 0.299);
                    int green = (int) (c.getGreen() * 0.587);
                    int blue = (int) (c.getBlue() * 0.114);
                    Color newColor = new Color(
                            red + green + blue,
                            red + green + blue,
                            red + green + blue);    			
    				graypic.setRGB(col, row, newColor.getRGB());
    					}
    				}

    	
    	//int y = 0; y < height; y++){
        //for(int x = 0; x < width; x++){
          //int p = img.getRGB(x,y);

          //int a = (p>>24)&0xff;
          //int r = (p>>16)&0xff;
          //int g = (p>>8)&0xff;
          //int b = p&0xff;

          //calculate average
          //int avg = (r+g+b)/3;

          //replace RGB value with avg
         /* p = (a<<24) | (avg<<16) | (avg<<8) | avg;

          //img.setRGB(x, y, p);
        }
        
      }*/
		return graypic;
	}


	 public static void main(String[] args) throws IOException 
	 {
   	 
		 ImageGrayscale  GrayImg = new ImageGrayscale ("C:\\DEEPTHI\\Masters\\2nd sem\\Algorithms\\Assignments\\IMAGE PROCESSING\\dump\\ImageProcessing\\src\\rezied.jpg", "output");
		 GrayImg.setSize(800, 600);
		 GrayImg.setVisible(true);
		 GrayImg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	
	   
	    }	
}//class ends here