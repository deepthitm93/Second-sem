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


public class ImageNegative extends JFrame{
	private static final long serialVersionUID = 1L;
	static int[][][] rgb_buffer;

	ImageNegative(String in, String out) {
		super("**Negative**");
		try {
			this.setLayout(new GridLayout(1, 2, 10, 10));
			JPanel img1 = new JPanel();
			JPanel img2 = new JPanel();
			File f1 = new File(in);
			File f2 = new File(out);
			BufferedImage image1 = ImageIO.read(f1);
			img1.add(new JLabel(new ImageIcon(image1)));
			BufferedImage image2 = negativescaleProcess(image1);
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

    //convert to Negative
    BufferedImage negativescaleProcess(BufferedImage image) {
		// TODO Auto-generated method stub
    		BufferedImage negpic = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

    		rgb_buffer = new int[3][image.getHeight()][image.getWidth()];
    		for (int row = 0; row < image.getHeight(); row++) {
    			for (int col = 0; col < image.getWidth(); col++) {
                    Color c = new Color(image.getRGB(col, row));
                    /*int red = (int) (c.getRed());
                    int green = (int) (c.getGreen());
                    int blue = (int) (c.getBlue());*/
                    int red=(int)(Math.abs( c.getRed( ) - 255 ));
                    int green=(int)(Math.abs( c.getGreen( ) - 255 ));
                    int blue=(int)(Math.abs( c.getBlue() - 255 ));
                    //green=255-green;
                    //blue=255-blue;
                    Color newColor = new Color(red,green,blue);
    				negpic.setRGB(col, row, newColor.getRGB());
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
		return negpic;
	}


	 public static void main(String[] args) throws IOException 
	 {
   	 
		 ImageNegative  Negative = new ImageNegative ("C:\\DEEPTHI\\Masters\\2nd sem\\Algorithms\\Assignments\\IMAGE PROCESSING\\dump\\ImageProcessing\\src\\rezied.jpg", "output");
		 Negative.setSize(800, 600);
		 Negative.setVisible(true);
		 Negative.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	
	   
	    }	
}//class ends here