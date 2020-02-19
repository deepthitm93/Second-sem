
/************************************************************
 * Image Fading Scale
 * Pass Pixel of one image to all the Pixel of other the Image 
 * 
 * 
 * 
 * 
 *Designed by Ganga Poudel and Deepthi TM
 ************************************************************ 
 */



import java.awt.Color;

public class ImageFading {

    public static Color combine(Color c1, Color c2, double alpha) {
        int Red = (int) (alpha * c1.getRed()   + (1 - alpha) * c2.getRed());
        int Green = (int) (alpha * c1.getGreen() + (1 - alpha) * c2.getGreen());
        int Blue = (int) (alpha * c1.getBlue()  + (1 - alpha) * c2.getBlue());
        return new Color(Red, Green, Blue);
    }

    public static void main(String[] args) {

        Picture picture1 = new Picture("nature.jpg");   // begin picture
        Picture picture2 = new Picture("Flower.jpg");   // end picture
        int width  = picture1.width();
        int height = picture1.height();
        Picture picture = new Picture(width, height);
        int n=20;
        for (int k = 0; k <= n; k++) {
            double alpha = 1.0 * k / n;
            for (int col = 0; col < width; col++) {
                for (int row = 0; row < height; row++) {
                    Color c1 = picture1.get(col, row);
                    Color c2 = picture2.get(col, row);
                    picture.set(col, row, combine(c2, c1, alpha));
                }
            }
            picture.show();
        }
    }
}