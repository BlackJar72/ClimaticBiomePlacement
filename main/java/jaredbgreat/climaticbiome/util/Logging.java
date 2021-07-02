package jaredbgreat.climaticbiome.util;	

import jaredbgreat.climaticbiome.Info;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A mostly unused wrapper for the Java logger, to make getting a logger easier.
 * 
 * @author Jared Blackburn
 */

public class Logging {
	private static Logger logger;
	private static Logger loggerr;
	private static Logging log;
	
	private Logging() {
		logger = LogManager.getLogger(Info.CHANNEL);
	}
	
	
	public static Logging getInstance() {
		if(log == null) {
			log = new Logging();
		}
		return log;
	}
	
	
	public static void logError(String error) {
		if(log == null) {
			log = new Logging();
		}
		System.err.println(error);
		logger.error(error);
	}
	
	
	public static void logInfo(String info) {
		if(log == null) {
			log = new Logging();
		}
		//new Exception().printStackTrace();
		logger.info(info);
	}

}
