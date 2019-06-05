package application.documentstypes;

import application.entities.EventEntity;
import application.entities.UserEntity;
import application.entities.template.TemplateEntity;
import application.entities.template.XYTextValue;
import application.exceptions.ExceptionMishpaha;
import application.models.event.IEventModel;
import application.models.user.IUserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Service
@Slf4j
public class JPGDocumentFormat {
    @Autowired
    IEventModel eventModel;
    @Autowired
    IUserModel userModel;

    private static Font getFont(String name, int size) {
        try {
            InputStream in = new FileInputStream(name);
            Font font = Font.createFont(Font.TRUETYPE_FONT, in);
            return font.deriveFont((float)size);
        } catch (Exception e) {
            log.info("getFont -> " + e.getMessage());
            return null;
        }
    }

    public void createInvitationFromTemplate(String template,
                                             String toFile,
                                             String fontname,
                                             Integer fontsize,
                                             TemplateEntity templateEntity,
                                             Integer eventId,
                                             Integer userId) throws IOException {
        BufferedImage myPicture = ImageIO.read(new File(template));
        Graphics2D g = (Graphics2D) myPicture.getGraphics();
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLUE);
        templateEntity.getItems().forEach((XYTextValue xyTextValue) -> {
            g.setFont(getFont(fontname, fontsize));
            log.info("createInvitationFromTemplate -> " + xyTextValue.getText());
            String[] textData = xyTextValue.getText().split("!");
            String textForPrint = "n/a";
            log.info("createInvitationFromTemplate -> " + Arrays.toString(textData));
            if (textData.length < 2) throw new RuntimeException("Internal error to template");
            if (textData[0].equals("user"))
            {
                //TODO
                try {
                    UserEntity userEntity = userModel.getById(userId);
                    textForPrint = userEntity.fieldByName(textData[1]);
                    log.info("createPictureFromTemplate => userentity{" + userId + "} => data for print " + textData[1] + " > value = " + textForPrint);
                } catch (ExceptionMishpaha exceptionMishpaha) {
                    log.info("createInvitationFromTemplate -> " + exceptionMishpaha.getMessage());
                }

            }
            if (textData[0].equals("event"))
            {
                try {
                    EventEntity eventEntity = eventModel.getById(eventId);
                    textForPrint = eventEntity.fieldByName(textData[1]);
                    log.info("createPictureFromTemplate => evententity{" + eventId + "} => data for print " + textData[1] + " > value = " + textForPrint);

                } catch (ExceptionMishpaha exceptionMishpaha) {
                    exceptionMishpaha.printStackTrace();
                }
                //TODO
            }
            log.info("createInvitationFromTemplate -> textForPrint = " + textForPrint);
            g.drawString(textForPrint, xyTextValue.getX(), xyTextValue.getY());
        });
        ImageIO.write(myPicture, "jpg", new File(toFile));
        log.info("createPictureFromTemplate => file of template is " + template + " > Save to file " + toFile + " > completed");
    }

}
