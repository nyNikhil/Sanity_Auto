package Demo;

import java.util.logging.LogManager;

import org.apache.logging.log4j.Logger;

public class Log4jDemo {
	
	 private static Logger logger = org.apache.logging.log4j.LogManager.getLogger(Log4jDemo.class);
	public static void main(String args[]){
		System.out.println("\n This is the Demo Program \n");
		logger.info("This is the information message");
		logger.error("This is the error message");
		logger.warn("This is warning message");
		logger.fatal("This is fatal message");
		
		System.out.println("Completed");
	}

}
