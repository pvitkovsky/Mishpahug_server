package Application;

import org.springframework.context.annotation.Configuration;

import jssc.SerialPort;
@Configuration
public class Options {
	public static final String HOSTADDRESS = "localhost";
	public static final String HOSTDATABASENAME = "home";
	public static final long DELAYTEST = 100L;
	public static final String DEBUG = "ON";
	public static final String SEPARATORLINE = "\n";
	public static final String SEPARATORFIELD = " , ";
}
