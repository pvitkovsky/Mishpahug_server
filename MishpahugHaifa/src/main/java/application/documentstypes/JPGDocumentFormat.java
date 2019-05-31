package application.documentstypes;

import application.entities.template.TemplateEntity;
import application.entities.template.XYTextValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class JPGDocumentFormat {

    public static void createInvitationFromTemplate(String template,
                                                    String toFile,
                                                    TemplateEntity templateEntity,
                                                    Integer eventId,
                                                    Integer userId) throws IOException {
        BufferedImage myPicture = ImageIO.read(new File(template));
        Graphics2D g = (Graphics2D) myPicture.getGraphics();
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLUE);
        templateEntity.getItems().forEach((XYTextValue xyTextValue) -> {
            g.setFont(new Font("Gabriola", 1,xyTextValue.getSize()));
            String[] textData = xyTextValue.getText().split(".");
            String textForPrint = "n/a";
            if (textData.length < 2) throw new RuntimeException("Error to template");
            if (textData[0].equals("event"))
            {
                //TODO
            }
            if (textData[0].equals("user"))
            {
                //TODO
            }
            g.drawString(textForPrint, xyTextValue.getX(), xyTextValue.getY());
        });
        ImageIO.write(myPicture, "jpg", new File(toFile));
        log.info("createPictureFromTemplate => file of template is " + template + " > Save to file " + toFile + " > completed");
    }

}
