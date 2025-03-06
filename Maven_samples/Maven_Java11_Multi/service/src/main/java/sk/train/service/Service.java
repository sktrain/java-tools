package sk.train.service;

public class Service {
	
	public static String getMessage() {
		return "Message from " + Service.class.getModule().getName();
	}

}
