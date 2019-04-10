package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class MishpohugApplication {

    static  {
        SimpleDateFormat dateFormatday = new SimpleDateFormat("dd");
        System.setProperty("current.date.day", dateFormatday.format(new Date()));
        SimpleDateFormat timeFormathours = new SimpleDateFormat("hh");
        System.setProperty("current.time.hours", timeFormathours.format(new Date()));
        SimpleDateFormat dateFormatmonth = new SimpleDateFormat("MM");
        System.setProperty("current.date.month", dateFormatmonth.format(new Date()));
        SimpleDateFormat timeFormatother = new SimpleDateFormat("mm-ss");
        System.setProperty("current.time", timeFormatother.format(new Date()));
        SimpleDateFormat dateFormatyear = new SimpleDateFormat("yyyy");
        System.setProperty("current.date.year", dateFormatyear.format(new Date()));
    }
    public static Logger log = Logger.getLogger(MishpohugApplication.class);




    public static void main(String[] args) {
        SpringApplication.run(MishpohugApplication.class, args);
    }

}
