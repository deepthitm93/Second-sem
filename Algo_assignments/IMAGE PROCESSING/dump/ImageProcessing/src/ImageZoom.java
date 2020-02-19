public class ImageZoom {

public static void main(String[] args) {

        Picture pic1 = new Picture("lunareclipsemoon.jpg");

        pic1.show();

        int width  = pic1.width();

        int height = pic1.height();

        Picture pic2 = new Picture(width, height);

        double cx = 0.5 * (width  - 1);

        double cy = 0.5 * (height - 1); 

        for (int x = 0; x < width; x++) {

            for (int y =0; y < height; y++) {

                double dx = x-cx;

                double dy = y-cy;

               // double radius = Math.sqrt(dx*dx + dy*dy);

                //double angle = (Math.PI)*2/256 * radius;

                //double angle = (Math.PI)/180;

//                int newx = (int) (dx * Math.cos(angle) + dy * Math.sin(angle) + cx);

                int newx = (int) (-dx * Math.cos(90) - dy * Math.sin(0) + cx);

                int newy = (int) (-dx * Math.sin(0) -dy * Math.cos(90) + cy);

 

              

                if (newx >= 0 && newx < width && newy >= 0 && newy < height)

                    pic2.set(x, y, pic1.get(newx, newy));

            }

        }

 

        pic2.show();

    }

 

  

}