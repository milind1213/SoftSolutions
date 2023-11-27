package com.sos.Utilities;

import java.util.Date;

public class TestUtils {
	
	 public void log(String message) {
	        Date timestamp = new Date();
	        String formattedMessage = "[" + timestamp + "] " + message;
	        System.out.println(formattedMessage);
	    }
}
