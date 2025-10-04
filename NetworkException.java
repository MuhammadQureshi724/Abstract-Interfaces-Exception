package Appliance;

public class NetworkException extends Exception {
	
	public NetworkException() { 
		super("Network Exception occurred"); 
	}
	
	public NetworkException(String message) { 
		super(message);
	}

}
