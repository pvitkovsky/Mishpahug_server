package application.documentstypes;

import ij.IJ;
import ij.ImagePlus;
import ij.io.FileSaver;
import ij.process.ImageProcessor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JPGDocumentFormat {

    public static void createPictureFromTemplete(String templete, String toFile) throws IOException {
        BufferedImage myPicture = ImageIO.read(new File(templete));
        Graphics2D g = (Graphics2D) myPicture.getGraphics();
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLUE);
        g.drawRect(10, 10, myPicture.getWidth() - 20, myPicture.getHeight() - 20);
        g.setFont(new Font("Gabriola", 1,128));
        g.drawString("Mishpahug 2019", 10, myPicture.getHeight() / 2);
        ImageIO.write(myPicture, "jpg", new File(toFile));
    }

}
