import java.awt.image.BufferedImage;
//import java.awt.image.WritableRaster;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
 

public class ImageHeqC extends JFrame{

	private static final long serialVersionUID = 1L;
	ImageHeqC(String in, String out){
        super("**Histogram Equalization**");
        try {
            this.setLayout(new GridLayout(1, 2, 10, 10));
            JPanel img1 = new JPanel();
            JPanel img2 = new JPanel();
            File f1 = new File(in);
            File f2 = new File(out);
            BufferedImage image1 = ImageIO.read(f1);
            img1.add(new JLabel(new ImageIcon(image1)));
            BufferedImage image2 =equalize(image1);
            ImageIO.write(image2, "jpg", f2);
            img2.add(new JLabel(new ImageIcon(image2)));
            this.add(img1);
            this.add(img2);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    BufferedImage equalize(BufferedImage src){
        BufferedImage nImg = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        int[] hr = new int[256];
        int[] hg = new int[256];
        int[] hb = new int[256];
        int TotalPixel = src.getWidth() * src.getHeight();
 
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                Color c = new Color(src.getRGB(x, y));
                hr[c.getRed()]++;
                hg[c.getGreen()]++;
                hb[c.getBlue()]++;
            }
        }
         
        int[] chr = new int[256];
        int[] chg = new int[256];
        int[] chb = new int[256];
        chr[0] = hr[0];
        chg[0] = hg[0];
        chb[0] = hb[0];
        for(int i=1;i<256;i++){
            chr[i] = chr[i-1] + hr[i];
            chg[i] = chg[i-1] + hg[i];
            chb[i] = chb[i-1] + hb[i];
        }
         
        float[] arr = new float[256];
        float[] arg = new float[256];
        float[] arb = new float[256];
        for(int i=0;i<256;i++){
            arr[i] =  (float)((chr[i]*255.0)/(float)TotalPixel);
            arg[i] =  (float)((chg[i]*255.0)/(float)TotalPixel);
            arb[i] =  (float)((chb[i]*255.0)/(float)TotalPixel);
        }
         
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                Color c = new Color(src.getRGB(x, y));
                Color nc = new Color((int)arr[c.getRed()], (int)arg[c.getGreen()], (int)arb[c.getBlue()]);
                nImg.setRGB(x, y, nc.getRGB());
            }
        }
        return nImg;
    }

    public static void main(String[] args) {

            ImageHeqC he = new ImageHeqC("C:\\DEEPTHI\\Masters\\2nd sem\\Algorithms\\Assignments\\IMAGE PROCESSING\\dump\\ImageProcessing\\src\\rezied.jpg","output");
            he.setSize(1024,900);
            he.setVisible(true);
            he.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
     
}